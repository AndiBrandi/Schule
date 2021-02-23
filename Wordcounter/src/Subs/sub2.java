package Subs;

public class sub2 implements Subscriber {
    @Override
    public void notifyMessage(String message) {
        if (message.contains("Corona")) {
            System.out.println("sub2: " + message);
        }
    }
}
