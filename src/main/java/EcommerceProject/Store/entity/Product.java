package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product<variant> implements Comparable<Product>{
	
	// Define attributes of Product class
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer iD;
	@Size(min=1, max=255, message="Give Your Product a Title!")
	private String title;
	private String description;
	private String slug;
	
	@Column(name="new_product")
	private boolean newProduct;
	
	@Column(name="featured_product")
	private boolean featuredProduct;
	
	@Column(name="active_product")
	private boolean activeProduct;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Brand brand;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Category category;

	@OneToMany(mappedBy="product")
	private List<ProductVariant> variants;

	private String imageUrl;
	private String color;
	private BigDecimal price;

//	Constructors
	protected Product() {

	}

	public Product(Integer iD, String description, String slug, Category category, Brand brand, boolean newProduct, boolean featuredProduct,
				   boolean activeProduct, String imageUrl, String color, BigDecimal price, List<ProductVariant> variants) {
		this.iD = iD;
		this.description = description;
		this.slug = slug;
		this.newProduct = newProduct;
		this.featuredProduct = featuredProduct;
		this.activeProduct = activeProduct;
		this.brand = brand;
		this.category = category;
		this.imageUrl = imageUrl;
		this.color = color;
		this.price = price;
		this.setTitle(setProductTitle());
		this.variants = variants;
	}

	// Setters & Getters methods
	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public boolean isFeaturedProduct() {
		return featuredProduct;
	}

	public void setFeaturedProduct(boolean featuredProduct) {
		this.featuredProduct = featuredProduct;
	}

	public boolean isActiveProduct() {
		return activeProduct;
	}

	public void setActiveProduct(boolean activeProduct) {
		this.activeProduct = activeProduct;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<ProductVariant> getVariant() {
		return variants;
	}

	public void setVariant(List<ProductVariant> variant) {
		this.variants = variants;
	}

	// Customising product title to be displayed in `isNew-Color-Brand` format
	public String setProductTitle(){
		try {
			if (this.isNewProduct()) {
				return "New-" + this.getColor().substring(0, 1).toUpperCase() + this.getColor().substring(1) + "-" + this.getBrand().getTitle().substring(0,1).toUpperCase() + this.getBrand().getTitle().substring(1);
			}
			return this.getColor().substring(0, 1).toUpperCase() + this.getColor().substring(1) + "-" + this.getBrand().getTitle().substring(0,1).toUpperCase() + this.getBrand().getTitle().substring(1);
		}catch(Exception e){
			throw new RuntimeException("Title is not set correctly");
		}
	}

	@Override
	public String toString() {
		return "Product [ID:" + getiD() +", Title:" + getTitle() + "]";
	}

	@Override
	public int compareTo(Product product) {
		int compare = getPrice().compareTo(product.getPrice());
		return compare;
	}
}
