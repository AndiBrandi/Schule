public interface IDBPublisher {

    void addSubscriber(IDBSubscriber subscriber);

    void removeSubscriber(IDBSubscriber subscriber);

    void sendName(String name);



}
