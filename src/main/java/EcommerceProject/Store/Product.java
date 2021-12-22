package EcommerceProject.Store;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Product implements Comparable<Product>{
	
	// Define attributes of Product class
	@Id
	@GeneratedValue
	private Integer ID;

	@Size(min=1, max=255, message="Give Your Product a Title!")
	private String Title;
	private String Description;
	private String Slug;
	
	@Column(name="new_product")
	private boolean New;
	
	@Column(name="featured_product")
	private boolean Featured;
	
	@Column(name="active_product")
	private boolean Active;
	
	@OneToMany(mappedBy="ID")
	private int BrandId;
	private String BrandTitle;
	
	@OneToMany(mappedBy="ID")
	private int CategoryId;
	private String CategoryTitle;
	private String ImageUrl;
	private String Color;
	private Double Price;
	
	//Constructors
//	public Product() {
//		super();
//	}
	
	public Product(Integer iD, String description, String slug, int category_id, String category_title, int brand_id, String brand_title, boolean new1, boolean featured,
			boolean active, String imageUrl, String color, Double price) {
		ID = iD;
		Description = description;
		Slug = slug;
		BrandId = brand_id;
		BrandTitle = brand_title;
		CategoryId = category_id;
		CategoryTitle = category_title;
		New = new1;
		Featured = featured;
		Active = active;
		ImageUrl = imageUrl;
		Color = color;
		Price = price;
		this.setTitle(setProductTitle());
	}

	// Setters & Getters methods
	public Integer getID() {
		return ID;
	}
	
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getSlug() {
		return Slug;
	}
	public void setSlug(String slug) {
		Slug = slug;
	}
	
	public boolean isNew() {
		return New;
	}
	public void setNew(boolean new1) {
		New = new1;
	}
	public boolean isFeatured() {
		return Featured;
	}
	public void setFeatured(boolean featured) {
		Featured = featured;
	}
	public boolean isActive() {
		return Active;
	}
	public void setActive(boolean active) {
		Active = active;
	}
	public int getBrand_id() {
		return BrandId;
	}
	public void setBrand_id(int brand_id) {
		BrandId = brand_id;
	}
	public int getCategory_id() {
		return CategoryId;
	}
	public void setCategory_id(int category_id) {
		CategoryId = category_id;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}

	public String getBrand_title() {
		return BrandTitle;
	}

	public void setBrand_title(String brand_title) {
		BrandTitle = brand_title;
	}

	public String getCategory_title() {
		return CategoryTitle;
	}

	public void setCategory_title(String category_title) {
		CategoryTitle = category_title;
	}

	// Customising product title to be displayed in `isNew-Color-Brand` format
	public String setProductTitle(){
		try {
			if (this.isNew()) {
				return "New-" + this.getColor().substring(0, 1).toUpperCase() + this.getColor().substring(1) + "-" + this.getBrand_title().substring(0, 1).toUpperCase() + this.getBrand_title().substring(1);
			}
			return this.getColor().substring(0, 1).toUpperCase() + this.getColor().substring(1) + "-" + this.getBrand_title().substring(0, 1).toUpperCase() + this.getBrand_title().substring(1);
		}catch(Exception e){
			throw new RuntimeException("Title is not set correctly");
		}
	}

	@Override
	public String toString() {
		return "Product [ID:" + getID() +", Title:" + getTitle() + "]";
	}

	@Override
	public int compareTo(Product product) {
		int compare = getPrice().compareTo(product.getPrice());
		return compare;
	}
}
