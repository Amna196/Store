package EcommerceProject.Store;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
	
	// Define attributes of Product class
	@Id
	@GeneratedValue
	private Integer ID;
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
	private List<Brand> Brands;
	
	@OneToMany(mappedBy="ID")
	private List<Category> Categories;
	private String ImageUrl;
	private String Color;
	private double Price;
	
	//Constructors
//	public Product() {
//		super();
//	}
	
	public Product(Integer iD, String title, String description, String slug, boolean new1, boolean featured,
			boolean active, String imageUrl, String color, double d) {
		ID = iD;
		Title = title;
		Description = description;
		Slug = slug;
		New = new1;
		Featured = featured;
		Active = active;
		ImageUrl = imageUrl;
		Color = color;
		Price = d;
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
	public List<Brand> getBrands() {
		return Brands;
	}
	public void setBrands(List<Brand> brands) {
		Brands = brands;
	}
	public List<Category> getCategories() {
		return Categories;
	}
	public void setCategories(List<Category> categories) {
		Categories = categories;
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
