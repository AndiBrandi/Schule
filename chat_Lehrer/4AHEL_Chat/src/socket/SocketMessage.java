package socket;

public class SocketMessage implements ISocketMessage {
    private String message = "";
    private ISocketConnection source = null;

    public SocketMessage(ISocketConnection source, String message) {
        this.message = message;
        this.source = source;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public ISocketConnection getSource() {
        return source;
    }

    @Override
    public void setSource(ISocketConnection src) {
        source = src;
    }
}
