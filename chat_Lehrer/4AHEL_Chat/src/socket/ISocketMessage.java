package socket;

public interface ISocketMessage {

    String getMessage();
    void setMessage(String msg);

    ISocketConnection getSource();
    void setSource(ISocketConnection src);

}
