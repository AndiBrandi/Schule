
package products.beer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("fillingQuantity")
    @Expose
    private Double fillingQuantity;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("pawn")
    @Expose
    private Double pawn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getFillingQuantity() {
        return fillingQuantity;
    }

    public void setFillingQuantity(Double fillingQuantity) {
        this.fillingQuantity = fillingQuantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPawn() {
        return pawn;
    }

    public void setPawn(Double pawn) {
        this.pawn = pawn;
    }

    @Override
    public String toString() {
        return name;
    }
}
