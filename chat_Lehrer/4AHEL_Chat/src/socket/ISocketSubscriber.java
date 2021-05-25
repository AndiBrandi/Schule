package socket;

public interface ISocketSubscriber {

    /**
     * Method is called if something happended
     * (message was received)
     * @param msg Message
     */
    void messageReceived(ISocketMessage msg);

}
