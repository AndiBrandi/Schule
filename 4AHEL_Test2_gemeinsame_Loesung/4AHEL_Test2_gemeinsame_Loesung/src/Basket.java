import shopconfig.Article;

import java.util.ArrayList;

public class Basket {

    private ArrayList<Article> basketList = new ArrayList<>();

    /**
     * @ToDo Fertigstellen der Methode
     *
     * Die Funktion addToBasket soll einen Artikel zum Warenkorb
     * (= ArrayList) hinzufügen.
     *
     * @param a Artikel der hinzugefügt werden soll.
     */
    public void addToBasket(Article a) {
        basketList.add(a);
    }

    /**
     * @ToDo Fertigstellen der Methode
     *
     * Die Funktion removeFromBasket soll einen Artikel aus dem Warenkorb
     * (= ArrayList) löschen.
     *
     * @param a Artikel der gelöscht werden soll.
     */
    public void removeFromBasket(Article a) {
        basketList.remove(a);
    }

    /**
     * @ToDo Fertigstellen der Methode
     *
     * Die Funktion getOrderPrice alle Artikel im Warenkorb durchlaufen
     * und deren Preis summieren.
     *
     * @return Preis der Bestellung
     */
    public double getOrderPrice() {
        double result = 0.0;

        for (Article a : basketList) {
            result = result + a.getPrice();
        }

        return result;
    }

}
