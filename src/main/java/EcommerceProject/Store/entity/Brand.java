package EcommerceProject.Store.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Brand")
public class Brand {
	
	// Define attributes of Brand class
	@Id
	@GeneratedValue
	private int iD;
	private String title;
	private String description;
	private String bannerImage;
	@OneToMany(mappedBy="iD")
	private List<Product> products;

	//Constructor
	public Brand() {

	}

	public Brand(int iD, String title, String description, String bannerImage) {
		this.iD = iD;
		this.title = title;
		this.description = description;
		this.bannerImage = bannerImage;
	}

	// Setters & Getters methods


	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
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

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	@Override
	public String toString() {
		return "Brand [id=" + iD +", Title=" + title + ", Description=" + description +"]";
	}
		
}
