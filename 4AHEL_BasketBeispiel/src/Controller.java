import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import products.electronics.Article;
import products.electronics.Shop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Controller {


    public Label gesamtpreislabel;
    public Button addButton;
    public Button removeButton;
    public ListView<Article> selectedproducts;
    public ListView<Article> availabelproducts;
    public Gson g = new Gson();

    public Basket myBasket = new Basket();


    public void initialize() {


        String result = "";
        String line;
        try {
            Shop kaufhaus = g.fromJson(new FileReader("shop.json"), Shop.class);

            availabelproducts.getItems().addAll(kaufhaus.getArticles());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void addproduct(ActionEvent actionEvent) {

        gesamtpreislabel.setText("");

        Article selected = availabelproducts.getSelectionModel().getSelectedItem();

        if (selected != null) {

            availabelproducts.getItems().remove(selected);
            selectedproducts.getItems().add(selected);
            myBasket.addToBasket(selected);

        }
    }

    public void removeproduct(ActionEvent actionEvent) {

        gesamtpreislabel.setText("");

        Article selected = selectedproducts.getSelectionModel().getSelectedItem();

        if(selected != null) {
            availabelproducts.getItems().add(selected);
            selectedproducts.getItems().remove(selected);

            myBasket.removeFromBasket(selected);
        }
    }

    public void calcprice(ActionEvent actionEvent) {



        DecimalFormat df = new DecimalFormat("##.##");

        gesamtpreislabel.setText(df.format(myBasket.getOrderPrice()) + " €");

    }
}

