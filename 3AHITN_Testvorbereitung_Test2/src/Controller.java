import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import products.beer.Brand;
import products.beer.Product;

import java.io.BufferedReader;
import java.io.FileReader;

public class Controller {
    public ListView<Product> productListView;
    public AnchorPane contentPane;

    public void initialize() {
        String result = "";

        try (BufferedReader br = new BufferedReader(new FileReader("response.json"))) {

            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson g = new Gson();
        Brand obj = g.fromJson(result, Brand.class);
        for (Product p : obj.getProducts()) {
            productListView.getItems().add(p);
        }
    }

    public void productListViewClicked(MouseEvent mouseEvent) {
        Product selected = productListView.getSelectionModel().getSelectedItem();

        if (selected != null) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("beerpanel.fxml"));
                Pane weatherpanel = loader.load();
                BeerpanelController controller = loader.getController();

                controller.setProduct(selected);
                AnchorPane.setRightAnchor(weatherpanel, 0.0);
                AnchorPane.setLeftAnchor(weatherpanel, 0.0);
                AnchorPane.setTopAnchor(weatherpanel, 0.0);
                AnchorPane.setBottomAnchor(weatherpanel, 0.0);

                contentPane.getChildren().clear();
                contentPane.getChildren().add(weatherpanel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
