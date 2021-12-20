package EcommerceProject.Store;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Brand {
	
	// Define attributes of Brand class
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
		public Brand(int iD, String title, String description, String bannerImage) {
			ID = iD;
			Title = title;
			Description = description;
			BannerImage = bannerImage;
		}

		// Setters & Getters methods
		public int getID() {
			return ID;
		}

		public void setID(int iD) {
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

		public String getBannerImage() {
			return BannerImage;
		}

		public void setBannerImage(String bannerImage) {
			BannerImage = bannerImage;
		}

//		public Product getProduct() {
//			return product;
//		}
//
//		public void setProduct(Product product) {
//			this.product = product;
//		}
		
		
		
		
		
}
