package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductVariant {


    @Id
    private String sku;
    private String size;
    private int quantity;

    @ManyToOne(fetch= FetchType.EAGER)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private CartItem cartItem;

    // Constructors
    public ProductVariant() {

    }

    public ProductVariant(String sku, String size, int quantity) {
        this.size = size;
        this.quantity = quantity;
        this.setSku(setSkuValue());
    }

    // Setters & getters methods
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Customising productVariant sku to be displayed in `ProductTitle-Size` format
    public String setSkuValue(){
        try {
            if (this.getProduct().getTitle() != null) {
                return this.getProduct().getTitle() + "-" + this.getSize().substring(0,1).toUpperCase() + "-" + this.getProduct().getiD();
            }
            return this.getSize().substring(0,1).toUpperCase() + "-" + this.getProduct().getiD();
        }catch(Exception e){
            throw new RuntimeException("sku is not set correctly");
        }
    }

    @Override
    public String toString() {
        return "Variant [sku=" + sku +", size=" + size + ", quantity=" + quantity + "]";
    }

}
