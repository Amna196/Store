package EcommerceProject.Store;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Product {
	
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
	private int Brand_id;
	
	@OneToMany(mappedBy="ID")
	private int Category_id;
	private String ImageUrl;
	private String Color;
	private double Price;
	
	//Constructors
//	public Product() {
//		super();
//	}
	
	public Product(Integer iD, String title, String description, String slug, int category_id, int brand_id, boolean new1, boolean featured,
			boolean active, String imageUrl, String color, double price) {
		ID = iD;
		Title = title; //call method to define the title
		Description = description;
		Slug = slug;
		Brand_id = brand_id;
		Category_id = category_id;
		New = new1;
		Featured = featured;
		Active = active;
		ImageUrl = imageUrl;
		Color = color;
		Price = price;
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
	public int getBrand() {
		return Brand_id;
	}
	public void setBrand(int brand_id) {
		Brand_id = brand_id;
	}
	public int getCategory() {
		return Category_id;
	}
	public void setCategory(int category_id) {
		Category_id = category_id;
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
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	
}
