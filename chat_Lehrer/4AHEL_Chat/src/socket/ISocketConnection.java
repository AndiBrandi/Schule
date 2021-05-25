package socket;

public interface ISocketConnection {

    void sendMessage(ISocketMessage message);

    void receiveMessage();

    void start();

    void stop();

    void setUsername(String username);

    String getUsername();

    void setChannel(String channel);

    String getChannel();

}
