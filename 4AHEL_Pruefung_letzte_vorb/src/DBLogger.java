public class DBLogger implements IDBSubscriber {


    @Override
    public void getName(String message) {
        System.out.println(message + " wurde hinzugefügt");
    }
}
