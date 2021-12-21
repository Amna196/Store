package EcommerceProject.Store;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Component annotation => means only a single instance of the annotated class gets created.
//Also in most cases this instance is automatically created on application startup.
@Component 
public class ProductDaoService {
	
	private static List<Category> categories = new ArrayList<>();
	static {
		categories.add(new Category(1,"c1","Very cool","http/tops.c1"));
		categories.add(new Category(2, "c2","Very nice","http/tops.c2"));
		categories.add(new Category(3, "c3","Very light","http/tops.c3"));
		categories.add(new Category(4, "c4","Very well","http/tops.c4"));
		categories.add(new Category(5, "c5","Very good","http/tops.c5"));
		
	}
	
	private static List<Brand> brands = new ArrayList<>();
	static {
		brands.add(new Brand(1,"b1","Very cool","http/tops.b1"));
		brands.add(new Brand(2,"b2","Very nice","http/tops.b2"));
		brands.add(new Brand(3,"b3","Very light","http/tops.b3"));
		brands.add( new Brand(4,"b4","Very well","http/tops.b4"));
		brands.add(new Brand(5,"b5","Very good","http/tops.b5"));
		
	}
	
	private static List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product(1,"Nike", "SportTop","First", categories.get(0).getID(), brands.get(0).getID(), false, true, true, "http/tops", "white", 19.750));
		
		products.add(new Product(2,"Ellese", "SportTop","Second",  categories.get(1).getID(), brands.get(1).getID(), true, false, true, "http/tops", "black", 25));
		
		products.add(new Product(3,"Adidas", "SportTop","Third",  categories.get(2).getID(), brands.get(2).getID(), true, true, true, "http/tops", "red", 15));
		
		products.add(new Product(4,"Twan", "Classic Top","Fourth",  categories.get(3).getID(), brands.get(3).getID(), false, true, true, "http/tops", "pink", 22));
		
		products.add(new Product(5,"Puma", "SportTop","Fifth",  categories.get(0).getID(), brands.get(0).getID(), true, true, true,"http/tops", "gray", 9.850));
		
	}
	
	//find all products
	public List<Product> findAll() {
		return products;
	}
	
	// get new products 
	public List<Product> findNew(boolean New) {
		List<Product> new_products = new ArrayList<>();
		for(Product product : products) { 
			if(product.isNew() == New) {
				new_products.add(product);
			}
		}
		return new_products;
	}
	
	// get featured products 
	public List<Product> findFeatured(boolean featured) {
		List<Product> featured_products = new ArrayList<>(); 
		for(Product product : products) { 
			if(product.isFeatured() == featured) {
				featured_products.add(product);
			}
		}
		return featured_products;
	}
		

	// get products upon category
	public List<Product> findByCategory(int category) {
		List<Product> productsCategory = new ArrayList<>(); //list of products of the same category

		for(Product product : products) {
			if( product.getCategory() == category){
				productsCategory.add(product);
				}
			}
			return productsCategory;
		}

	// get products upon brand
	public List<Product> findByBrand(int brand_id) {
		List<Product> productsBrand = new ArrayList<>(); //list of products of the same brand
		for(Product product : products) {
			if(product.getBrand() == brand_id) {
				productsBrand.add(product);

			}
		}
		return productsBrand;
	}

	public List<Product> findAll(Sort.Direction asc, String sortBy) {
		List<Product> sortedAsc = new ArrayList<>();
		products.stream().sorted();
		return sortedAsc;
	}


//	//save product to the list
//	public Product save(Product product) {
//		if(product.getId() == null) {
//			product.setId(++countProduct);
//		}
//		products.add(product);
//		return product;
//	}
//		
//		// delete Product by id
//		public Product deleteById(int id) {
//			Iterator<Product> iterator = products.iterator();
//			while(iterator.hasNext()) { 
//				Product product = iterator.next();
//				if(product.getID() == id) {
//					iterator.remove();
//					return product;
//				}
//			}
//			return null;
//		}
	
	
}
