package EcommerceProject.Store.service;

import EcommerceProject.Store.entity.Brand;
import EcommerceProject.Store.entity.Category;
import EcommerceProject.Store.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Component annotation => means only a single instance of the annotated class gets created.
//Also in most cases this instance is automatically created on application startup.
@Component
public class ProductDaoService{
	
	private static final List<Category> categories = new ArrayList<>();
	static {
		categories.add(new Category(1,"Crew Neck style","It is characterized by a round, circular neckline that fits snugly at the neck.","https://www.google.com/url"));
		categories.add(new Category(2, "V Neck style","this type of t-shirt forms a V shape at the neck.","https://www.google.com/url"));
		categories.add(new Category(3, "Plain t shirt style","Plain t-shirts are the staple of a man’s wardrobe. They are called classic for a reason after all.","https://www.google.com/url"));
		categories.add(new Category(4, "Raglan sleeve types","which are available in three quarter and full- length sleeves.","https://www.google.com/url"));
		categories.add(new Category(5, "Hooded style","Its athleisure appeal is perfect for the gym travel or while playing football with your homies.","https://www.google.com/url"));
		
	}
	
	private static final List<Brand> brands = new ArrayList<>();
	static {
		brands.add(new Brand(1,"nike","nike is cool","https://www.google.com/imgres?"));
		brands.add(new Brand(2,"ellese","ellese is nice","https://www.google.com/url?sa=i&url=https%3A%2F%2F1000logos.net%2Fellesse-logo%2F&psig=AOvVaw2SghPRTL0vK4HgCyuC35Up&ust=1640331541895000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCIDks6m1-fQCFQAAAAAdAAAAABAD"));
		brands.add(new Brand(3,"adidas","ellese is nice","https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.ietp.com%2Ffr%2Fdfeddfshop%2Fsd%2F00130%2Fadidas%2F&psig=AOvVaw1gGxnf7wixK4t6D5GRHupG&ust=1640331591491000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLjJ0sC1-fQCFQAAAAAdAAAAABAJ"));
		brands.add( new Brand(4,"twan","twan is great","https://www.google.com/url?sa=i&url=https%3A%2F%2Fmobile.twitter.com%2Fdesigntwan&psig=AOvVaw3chNu1FuGDK2zw3AS8WU9Y&ust=1640331625241000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCNDv49G1-fQCFQAAAAAdAAAAABAD"));
		brands.add(new Brand(5,"puma","puma is cood","https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FPuma_(brand)&psig=AOvVaw0DB4ITY7L4v7zyjJBmqSrx&ust=1640331662557000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCOCsiuO1-fQCFQAAAAAdAAAAABAD"));
		
	}
	
	private static final List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product(1,"SportTop","First", categories.get(0), brands.get(0), false, true, true, "https://cdn.tamanna.com/assets/foot_locker/nike/317344050080_01.jpg?quality=80&format=auto&width=1000", "white", 19.750));
		
		products.add(new Product(2, "SportTop","Second",  categories.get(1), brands.get(1), true, false, true, "https://www.yoox.com/images/items/12/12518052WC_14_f.jpg?impolicy=crop&width=387&height=490", "black", 25.0));
		
		products.add(new Product(3, "SportTop","Third",  categories.get(2), brands.get(2), true, true, true, "https://adidasm2-cdn.revton.com/media/catalog/product/cache/840x840/1706b311b4e139861e83dd948758a3de/h/3/h37756-10.jpg", "red", 15.0));
		
		products.add(new Product(4,"Classic Top","Fourth",  categories.get(3), brands.get(3), false, true, true, "https://cdn-images.farfetch-contents.com/15/27/32/97/15273297_27082626_1000.jpg", "pink", 22.0));
		
		products.add(new Product(5,"SportTop","Fifth",  categories.get(0), brands.get(0), true, true, true,"https://static.aawweb.com/media/catalog/product/cache/9f18371e3a457e456c922dbc54690d4f/1/c/1c03b57c2fd58a611a0f2bc09c97e2fca28e789fee447edec90831ec5a09e899.jpeg", "gray", 10.500));
		
	}
	
	//find all products
	public List<Product> findAll() {
		return products;
	}
//	//TODO: PAGABLE LIST
//	public Page<Product> findAllPagable(Pageable pageRequest) {
//		productRepository.saveAll(products);
//		Page<Product> productDataList = productRepository.findAll(pageRequest);
//		return productDataList;
//	}

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
	public List<Product> findByCategory(String category) {
		List<Product> productsCategory = new ArrayList<>();
		for(Product product : products) {
			if(product.getCategory().getTitle().equals(category)){
				productsCategory.add(product);
				}
			}
			return productsCategory;
		}

	// get products upon brand
	public List<Product> findByBrand(String brand) {
		List<Product> productsBrand = new ArrayList<>();
		for(Product product : products) {
			if(product.getBrand().getTitle().equals(brand)) {
				productsBrand.add(product);

			}
		}
		return productsBrand;
	}

	// sort products upon price in ascending order
	public List<Product> sortByPriceAsc(){
		Collections.sort(products);
		return products;
	}

	// sort products upon price in descending order
	public List<Product> sortByPriceDesc(){
		Collections.sort(products);
		Collections.reverse(products);
		return products;
	}

//	// find all products in pagination
//	public List<Product> getProductsList(int pageNo, int pageSize) {
////		List<Product> productList = new ArrayList<>();
//		Pageable paging =  PageRequest.of(pageNo, pageSize);
//
//		Page<Product> pagedResult = productRepository.findAll(paging);
//
//		System.out.println("total element.... "  +   pagedResult.getTotalElements());
//		System.out.println("total pages.... "  +   pagedResult.getTotalPages());
//		System.out.println("current page.... "  +   pagedResult.getNumber());
//		System.out.println("no of elements in a page.... "  +   pagedResult.getNumberOfElements());
//		if(pagedResult.hasContent()) {
//			products = pagedResult.getContent();
//			return pagedResult.getContent();
//		} else {
//			return products;
//		}
//	}


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
