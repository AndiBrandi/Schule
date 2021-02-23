import com.google.gson.Gson;
import employees.Department;
import employees.Employee;
import employees.Firma;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Controller implements IDBPublisher {

    public ListView<Department> departmentsListView;
    public ListView<Employee> employeesListView;
    public Label firstnameLabel;
    public Label lastnameLabel;
    public Label mailLabel;
    public Label phoneLabel;
    public TextField nameTextField;
    public Gson g = new Gson();

    public ArrayList<IDBSubscriber> subscribers = new ArrayList<>();

    public void initialize() {

        try {
            Firma firma = g.fromJson(new FileReader("departments.json"), Firma.class);

            departmentsListView.getItems().addAll(firma.getDepartments());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public void departmentsListViewClicked(MouseEvent mouseEvent) {

        employeesListView.getItems().clear();

        Department dep = departmentsListView.getSelectionModel().getSelectedItem();

        employeesListView.getItems().addAll(dep.getEmployees());


    }

    public void employeessListViewClicked(MouseEvent mouseEvent) {

        Employee emp = employeesListView.getSelectionModel().getSelectedItem();

        firstnameLabel.setText(emp.getFirstname());
        lastnameLabel.setText(emp.getLastname());
        mailLabel.setText(emp.getMail());
        phoneLabel.setText(emp.getPhone());

    }

    public void addButtonClicked(ActionEvent actionEvent) {

        addName(nameTextField.getText());
        nameTextField.clear();

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
    public int addName(String message) {

        for(IDBSubscriber element : subscribers) {

            element.getName(message);
        }

        return 0;
    }
}
