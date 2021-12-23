package EcommerceProject.Store.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category {
	
	// Define attributes of Category class
	@Id
	@GeneratedValue
	private int ID;
	private String Title;
	private String Description;
	private String BannerImage;
	@OneToMany(mappedBy="ID")
	private List<Product> products;
	
	//Constructor

	public Category() {
	}

	public Category(int iD, String title, String description, String bannerImage) {
		ID = iD;
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
	@Override
	public String toString() {
		return "Category [id=" + ID +", Title=" + Title + ", Description=" + Description +"]";
	}
	
	
}
