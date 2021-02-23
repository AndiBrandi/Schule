import com.google.gson.Gson;
import example.Brand;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class Controller {
    public Label label_ID;
    public Label label_Name;
    public Label label_description;
    public Label label_fillamount;
    public Label label_price;
    public Label label_bottles;
    public Label label_pfand;
    public Label label_finalprice;
    public ListView listview;

    public void initialize() {
        getProduct();
    }

    public void getProduct() {
        Integer ID;
        String Name;
        String Description;
        Double fillamount;
        Double price;
        Integer bottles;
        Double pfand;
        double finalprice;

        Getraenkerequest request = new Getraenkerequest();
        String response = request.readfile();
        Gson parser = new Gson();
        Brand parsedObject = parser.fromJson(response, Brand.class);

        for (int i = 0; i < parsedObject.getProducts().size(); ++i) {
            listview.getItems().add(parsedObject.getProducts().get(i).getName());
        }


       /*
        ID = parsedObject.getId();
        Name = parsedObject.getName();
        Description = parsedObject.getDesc();
        fillamount = parsedObject.getFillingQuantity();
        price = parsedObject.getPrice();
        bottles = parsedObject.getAmount();
        pfand = parsedObject.getPawn();
        finalprice = (pfand + price) * bottles;
        label_ID.setText(String.valueOf(ID));
        label_bottles.setText(String.valueOf(bottles));
        label_description.setText(Description);
        label_fillamount.setText(String.valueOf(fillamount));
        label_price.setText(String.valueOf(price));
        label_pfand.setText(String.valueOf(pfand));
        label_Name.setText(Name);
        label_finalprice.setText(String.valueOf(finalprice));*/
    }

    public void ListViewClicked(MouseEvent mouseEvent) {
        Getraenkerequest request = new Getraenkerequest();
        String response = request.readfile();
        Gson parser = new Gson();
        Brand parsedObject = parser.fromJson(response, Brand.class);
        Double pawn;
        Double price;
        Double result;

        for (int i = 0; i < parsedObject.getProducts().size(); ++i) {
            if(listview.getItems().get(listview.getSelectionModel().getSelectedIndex()).equals(parsedObject.getProducts().get(i).getName())){
                label_ID.setText(parsedObject.getProducts().get(i).getId().toString());
                label_Name.setText(parsedObject.getProducts().get(i).getName().toString());
                label_description.setText(parsedObject.getProducts().get(i).getDesc().toString());
                label_fillamount.setText(parsedObject.getProducts().get(i).getFillingQuantity().toString());
                label_pfand.setText(parsedObject.getProducts().get(i).getPawn().toString());
                label_bottles.setText(parsedObject.getProducts().get(i).getAmount().toString());
                pawn = parsedObject.getProducts().get(i).getPawn();
                price = parsedObject.getProducts().get(i).getPrice();
                result = pawn + price;
                label_finalprice.setText(result.toString());
                label_price.setText(parsedObject.getProducts().get(i).getPrice().toString());
            }
        }
    }
}
