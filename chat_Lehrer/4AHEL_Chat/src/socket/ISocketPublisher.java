package socket;

public interface ISocketPublisher {

    void notifySubscribers(ISocketMessage msg);

    void addSubscriber(ISocketSubscriber sub);

    void removeSubscriber(ISocketSubscriber sub);

}
