
package departments.person;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department_ {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("employees")
    @Expose
    private List<Employee> employees = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String toString() {
        return name;
    }

}
