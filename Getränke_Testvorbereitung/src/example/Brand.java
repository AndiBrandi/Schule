
package example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Brand {

    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
