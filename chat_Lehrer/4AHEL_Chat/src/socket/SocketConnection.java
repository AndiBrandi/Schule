/**
 * @ToDo Benutzername setzen
 * Die erste Nachricht die der Client an den Server schickt
 * ist der Benutzername.
 * Dh.: Senden Sie nach dem Aufbauen der Verbindung eine Nachricht,
 * die den Inhalt des Benutzer - Textfelds enthölt.
 */

package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketConnection implements ISocketConnection, ISocketPublisher {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private String host = "";
    private int port = 0;
    private Socket socket = null;
    private boolean running = false;
    private PrintWriter out = null;
    private Scanner in = null;
    private String username = "";
    private String channel = "Main";


    private final ArrayList<ISocketSubscriber> subscribers = new ArrayList<>();

    public SocketConnection(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            this.socket = new Socket(host, port);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
        }
    }

    public SocketConnection(Socket incoming) {
        this.host = incoming.getInetAddress().getHostAddress();
        this.port = incoming.getPort();

        try {
            this.socket = incoming;

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
        }
    }

    @Override
    public void sendMessage(ISocketMessage message) {
        executor.execute(
                new Thread() {
                    @Override
                    public void run() {
                        out.println(message.getMessage());
                    }
                }
        );
    }

    @Override
    public void receiveMessage() {
        /**
         * start new thread for receiving messages
         * asynchronously.
         */
        new Thread() {
            @Override
            public void run() {
                while (running) {
                    String message = in.nextLine();

                    // notify all subscribers, that something happened
                    SocketMessage obj = new SocketMessage(SocketConnection.this, message);
                    // SocketConnection.this works only from "inner classes"
                    // --> Current location: Class Thread located in class SocketConnection
                    notifySubscribers(obj);
                }
            }
        }.start();
    }

    @Override
    public void start() {
        running = true;

        receiveMessage();
    }

    @Override
    public void stop() {
        running = false;

        try {
            executor.shutdown();

            while (!executor.isTerminated()) ; // Busy waiting

            socket.close();
        } catch (IOException e) {

        }
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String getChannel() {
        return channel;
    }

    @Override
    public void notifySubscribers(ISocketMessage msg) {
        for (ISocketSubscriber sub : subscribers) {
            sub.messageReceived(msg);
        }
    }

    @Override
    public void addSubscriber(ISocketSubscriber sub) {
        if (!subscribers.contains(sub)) {
            subscribers.add(sub);
        }
    }

    @Override
    public void removeSubscriber(ISocketSubscriber sub) {
        subscribers.remove(sub);
    }
}