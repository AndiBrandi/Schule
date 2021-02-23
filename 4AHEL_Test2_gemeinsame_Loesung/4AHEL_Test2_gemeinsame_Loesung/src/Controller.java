import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import shopconfig.Article;
import shopconfig.Shop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;

public class Controller {
    public ListView<Article> availableListView;
    public ListView<Article> basketListView;

    public Label priceDescLabel;
    public Label priceValueLabel;

    private Basket myBasket = new Basket();

    public void initialize() {
        /**
         * Verstecke die beiden Labels
         */
        priceDescLabel.setVisible(false);
        priceValueLabel.setVisible(false);

        Gson g = new Gson();
        try {
            Shop myShop = g.fromJson(new FileReader("shop.json"), Shop.class);

            availableListView.getItems().addAll(myShop.getArticles());

        } catch (FileNotFoundException e) {
        }
    }

    public void addClicked(ActionEvent actionEvent) {
        Article selected = availableListView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            availableListView.getItems().remove(selected);
            basketListView.getItems().add(selected);

            myBasket.addToBasket(selected);

            priceDescLabel.setVisible(false);
            priceValueLabel.setVisible(false);
        }
    }

    public void removeClicked(ActionEvent actionEvent) {
        Article selected = basketListView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            availableListView.getItems().add(selected);
            basketListView.getItems().remove(selected);

            myBasket.removeFromBasket(selected);

            priceDescLabel.setVisible(false);
            priceValueLabel.setVisible(false);
        }
    }

    public void calcClicked(ActionEvent actionEvent) {
        /**
         * Verstecke die beiden Labels
         */
        priceDescLabel.setVisible(true);
        priceValueLabel.setVisible(true);

        DecimalFormat df = new DecimalFormat("##.##");

        priceValueLabel.setText(df.format(myBasket.getOrderPrice()) + " €");
    }
}
