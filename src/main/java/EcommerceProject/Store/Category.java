package EcommerceProject.Store;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	// Define attributes of Category class
	@Id
	@GeneratedValue
	private int ID;
	private String Title;
	private String Description;
	private String BannerImage;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Product product;
	
	//Constructor
	public Category(String title, String description, String bannerImage) {
		super();
		Title = title;
		Description = description;
		BannerImage = bannerImage;
	}
	
	// Setters & Getters methods
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
	public String getBannerImage() {
		return BannerImage;
	}
	public void setBannerImage(String bannerImage) {
		BannerImage = bannerImage;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
	
}
