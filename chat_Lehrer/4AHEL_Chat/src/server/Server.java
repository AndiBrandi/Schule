package server;

import socket.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements IServer {
    private int port = 0;
    private ServerSocket server = null;
    private boolean running = false;

    private ArrayList<ISocketConnection> clients = new ArrayList<>();

    public Server(int port) {
        this.port = port;
    }

    private void systemMessage(ISocketMessage msg) {
        if (msg.getMessage().contains("{DISCONNECT}")) {
            clients.remove(msg.getSource()); // remove user from list of all clients
        } else if (msg.getMessage().contains("{NEWUSER}")) {
            // new user joined the chat!
            String username = msg.getMessage().split(" ")[1];
            msg.getSource().setUsername(username); // set username for connection
        }

        String allUsers = listUsers(msg.getSource().getChannel());

        SocketMessage newUser = new SocketMessage(msg.getSource(), "{USERS} " + allUsers);

        broadcastMessage(newUser);
    }

    private String listUsers(String channel) {
        String allUsers = "";

        for (ISocketConnection c : clients) {
            if (c.getChannel().equals(channel)) { // list all users within the same channel
                if (allUsers.length() > 0) {
                    allUsers += ";";
                }

                allUsers += c.getUsername();
            }
        }

        return allUsers;
    }

    private void broadcastMessage(ISocketMessage msg) {
        broadcastMessage(msg, msg.getSource().getChannel());
    }

    private void broadcastMessage(ISocketMessage msg, String channel) {
        for (ISocketConnection c : clients) {
            if (c.getChannel().equals(channel)) {
                c.sendMessage(msg);
            }
        }
    }

    private void privateMessage(ISocketMessage msg) {
        // schneide alles zwischen "@" und dem ersten " " aus!
        // @peter testnachricht --> würde peter liefern
        String receiver = msg.getMessage().substring(1, msg.getMessage().indexOf(" "));

        if (receiver.length() > 0) {
            for (ISocketConnection c : clients) {
                if (c.getUsername().equals(receiver)) {
                    // c ist der Empfänger -> schicke die Nachricht an c!
                    String message = msg.getMessage().substring(msg.getMessage().indexOf(" ") + 1);

                    message = "Private Nachricht von " + msg.getSource().getUsername() + ": " + message;

                    c.sendMessage(new SocketMessage(msg.getSource(), message));
                } else if (c == msg.getSource()) {
                    // c ist der Sender -> schicke die Nachricht an c!
                    String message = msg.getMessage().substring(msg.getMessage().indexOf(" ") + 1);

                    message = "Private Nachricht an " + receiver + ": " + message;

                    c.sendMessage(new SocketMessage(msg.getSource(), message));
                }
            }
        }
    }

    private void changeChannel(ISocketMessage msg) {
        String oldChannel = msg.getSource().getChannel(); // actual channel
        String newChannel = msg.getMessage().replace("@channel ", "");

        msg.getSource().setChannel(newChannel);

        String users  = listUsers(oldChannel);
        SocketMessage userMessage = new SocketMessage(msg.getSource(), "{USERS} " + users);
        broadcastMessage(userMessage, oldChannel);

        users  = listUsers(newChannel);
        userMessage = new SocketMessage(msg.getSource(), "{USERS} " + users);
        broadcastMessage(userMessage, newChannel);

    }

    @Override
    public void handleConnections() {
        System.out.println("Listening for incoming connections...");

        ISocketSubscriber broadcastSubscriber = new ISocketSubscriber() {
            @Override
            public void messageReceived(ISocketMessage msg) {
                System.out.println("Nachricht erhalten: " + msg.getMessage());

                if (msg.getMessage().contains("{") && msg.getMessage().contains("}")) {
                    systemMessage(msg);
                } else if (msg.getMessage().charAt(0) == '@') { // die nachricht startet mit einem @
                    if (msg.getMessage().startsWith("@channel")) {
                        changeChannel(msg);
                    } else {
                        // private nachricht
                        privateMessage(msg);
                    }
                } else {
                    SocketMessage broadCastMessage =
                            new SocketMessage(
                                    msg.getSource(),
                                    msg.getSource().getUsername() +
                                            ": " +
                                            msg.getMessage()
                            );

                    broadcastMessage(broadCastMessage);
                }
            }
        };

        new Thread() {
            @Override
            public void run() {
                while (running) {
                    try {
                        Socket incoming = server.accept(); // new client connects to the server

                        System.out.println("New incoming connection: " + incoming.getInetAddress().getHostAddress());

                        SocketConnection client = new SocketConnection(incoming); // wrap client into SocketConnection class
                        clients.add(client); // add client to the list of all clients

                        client.addSubscriber(broadcastSubscriber); // add broadcastSubscriber (which is called, when a messages was received) to the connection

                        client.start(); // start receiving messages
                    } catch (IOException e) {
                    }
                }
            }
        }.start();
    }

    @Override
    public void start() throws IOException {
        System.out.println("Starting server...");

        running = true;
        server = new ServerSocket(port);

        handleConnections();
    }

    @Override
    public void stop() {
        running = false;

        try {
            server.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        Server chatServer = new Server(5432); // create new server instance, running on port 5432

        try {
            chatServer.start(); // start server instance and listen for incoming connections
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
