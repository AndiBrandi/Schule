import com.google.gson.Gson;
import departments.person.Firma;
import departments.person.Department_;
import departments.person.Employee;
import javafx.event.ActionEvent;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class Controller implements IDBPublisher {


    public ListView<Department_> departmentsListView;
    public ListView<Employee> employeesListView;
    public Label firstnameLabel;
    public Label lastnameLabel;
    public Label mailLabel;
    public Label phoneLabel;
    public TextField nameTextField;
    public Gson g = new Gson();
    public String obj;

    public ArrayList<IDBSubscriber> subscribers = new ArrayList<>();

    DBLogger logger = new DBLogger();

    public void initialize() {

        String result = "";

        try (BufferedReader br = new BufferedReader(new FileReader("departments.json"))) {

            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson g = new Gson();
        Firma obj = g.fromJson(result, Firma.class);
        for (Department_ dep : obj.getDepartments()) {
            departmentsListView.getItems().add(dep);
        }

    }

    public void departmentsListViewClicked(MouseEvent mouseEvent) {

        employeesListView.getItems().clear();

        Department_ selected = departmentsListView.getSelectionModel().getSelectedItem();

        for(Employee emp : selected.getEmployees()) {

            employeesListView.getItems().addAll(emp);
        }


    }

    public void employeessListViewClicked(MouseEvent mouseEvent) {

        Employee selected = employeesListView.getSelectionModel().getSelectedItem();

        firstnameLabel.setText(selected.getFirstname());
        lastnameLabel.setText(selected.getLastname());
        mailLabel.setText(selected.getMail());
        phoneLabel.setText(selected.getPhone());


    }

    public void addButtonClicked(ActionEvent actionEvent) {

        if(nameTextField.getText().length()>0) {

            sendName(nameTextField.getText());
            nameTextField.clear();

        }


    }

    @Override
    public void addSubscriber(IDBSubscriber subscriber) {

        if(!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }

    }

    @Override
    public void removeSubscriber(IDBSubscriber subscriber) {

        subscribers.remove(subscriber);

    }

    @Override
    public void sendName(String name) {

        for(IDBSubscriber element : subscribers) {
            element.getName(name);
        }

    }
}
