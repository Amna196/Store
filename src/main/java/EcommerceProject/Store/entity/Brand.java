package EcommerceProject.Store.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Brand")
public class Brand {
	
	// Define attributes of Brand class
		@Id
		@GeneratedValue
		private int ID;
		private String Title;
		private String Description;
		private String BannerImage;
		@OneToMany(mappedBy="ID")
		private List<Product> products;

		//Constructor
		public Brand() {

		}

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

	@Override
	public String toString() {
		return "Brand [id=" + ID +", Title=" + Title + ", Description=" + Description +"]";
	}
//		public Product getProduct() {
//			return product;
//		}
//
//		public void setProduct(Product product) {
//			this.product = product;
//		}
		
		
		
		
		
}
