package Subs;

public class sub1 implements Subscriber{
    @Override
    public void notifyMessage(String message) {
        System.out.println("sub1: "+message);
    }
}
