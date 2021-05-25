package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import socket.ISocketMessage;
import socket.ISocketSubscriber;
import socket.SocketConnection;
import socket.SocketMessage;

public class Controller {
    public TextField hostTextField;
    public TextField portTextField;
    public TextField usernameTextField;
    public Button connectButton;
    public TextArea inTextArea;
    public TextArea outTextArea;
    public Button sendButton;
    public ListView<String> usersListView;

    private SocketConnection connection = null;

    public void connectButtonClicked(ActionEvent actionEvent) {
        connection =
                new SocketConnection(
                        hostTextField.getText(),
                        Integer.parseInt(portTextField.getText())
                );

        hostTextField.setDisable(true);
        portTextField.setDisable(true);

        ISocketSubscriber incomingMessageSubscriber =
                new ISocketSubscriber() {
                    @Override
                    public void messageReceived(ISocketMessage msg) {
                        if (msg.getMessage().contains("{USERS}")) {
                            String users = msg.getMessage().split(" ")[1];
                            String[] userArray = users.split(";");

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    usersListView.getItems().clear();
                                    usersListView.getItems().addAll(userArray);
                                }
                            });
                        } else {
                            inTextArea.appendText(msg.getMessage() + "\n");
                        }
                    }
                };

        connection.addSubscriber(incomingMessageSubscriber);

        connection.start(); // start listening for incoming messages

        connection.setUsername(usernameTextField.getText());

        connection.sendMessage(new SocketMessage(connection, "{NEWUSER} " + usernameTextField.getText()));
    }

    public void sendButtonClicked(ActionEvent actionEvent) {
        if (connection != null && outTextArea.getText().length() > 0) {
            connection.sendMessage(
                    new SocketMessage(connection, outTextArea.getText())
            );

            outTextArea.clear();
        }
    }
}
