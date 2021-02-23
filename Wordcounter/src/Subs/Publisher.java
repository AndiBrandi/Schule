package Subs;

public interface Publisher {
    public void addSubscriber(Subscriber subscriber);

    public void removeSubscriber(Subscriber subscriber);

    public void notifyMessages(String message);
}
