package EcommerceProject.Store;

public class ProductVariant {

    private String sku;
    private String size;
    private int quantity;
    private int product_id;

    // Constructor
    public ProductVariant(String sku, String size, int quantity, int product_id) {
        this.sku = sku;
        this.size = size;
        this.quantity = quantity;
        this.product_id = product_id;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

}
