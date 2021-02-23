import javafx.beans.binding.DoubleExpression;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import products.beer.Product;

import java.text.DecimalFormat;

public class BeerpanelController {
    public Label idLabel;
    public Label nameLabel;
    public Label descLabel;
    public Label fillingQuantityLabel;
    public Label amountLabel;
    public Label priceLabel;
    public Label pawnLabel;
    public Label totalPriceLabel;

    public void setProduct(Product p) {
        DecimalFormat df = new DecimalFormat("#.##");
        idLabel.setText(Integer.toString(p.getId()));
        nameLabel.setText(p.getName());
        descLabel.setText(p.getDesc());
        fillingQuantityLabel.setText(df.format(p.getFillingQuantity()) + " Liter");
        amountLabel.setText(Integer.toString(p.getAmount()));
        priceLabel.setText(df.format(p.getPrice()) + " Euro");
        pawnLabel.setText(df.format(p.getPrice()) + " Euro");
        totalPriceLabel.setText(df.format(p.getAmount() * (p.getPrice() + p.getPawn())) + " Euro");
    }
}
