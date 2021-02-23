import java.util.concurrent.Flow;

public interface IDBPublisher {

    void addSubscriber(IDBSubscriber subscriber);

    void removeSubscriber(IDBSubscriber subscriber);

    int addName(String message);

}
