import static org.junit.jupiter.api.Assertions.*;

import employees.Department;
import employees.Employee;
import employees.Firma;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;
import java.io.FileReader;


class ControllerTest {

    public Department dep;

    public Controller controller = new Controller();

    @org.junit.jupiter.api.Test
    void initialize() {
    }

    @org.junit.jupiter.api.Test
    void departmentsListViewClicked() {
        int i = 0;
        try {
            Firma firma = controller.g.fromJson(new FileReader("departments.json"), Firma.class);

            for ( Department dep : firma.getDepartments()) {
                ++i;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(3, i);

    }

    @org.junit.jupiter.api.Test
    void employeessListViewClicked() {

//        int i = 0;
//        try {
//            Department dep = controller.g.fromJson(new FileReader("departments.json"), Department.class);
//
//            if(dep != null) {
//
//                for (Employee emp : dep.getEmployees()) {
//                    ++i;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Assertions.assertEquals(3, i);

    }

    @org.junit.jupiter.api.Test
    void addButtonClicked() {
    }
}