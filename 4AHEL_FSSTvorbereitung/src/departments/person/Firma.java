
package departments.person;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Firma {

    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("departments")
    @Expose
    private List<Department_> departments = null;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Department_> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department_> departments) {
        this.departments = departments;
    }


}
