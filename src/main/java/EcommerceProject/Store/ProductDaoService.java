package EcommerceProject.Store;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Component annotation => means only a single instance of the annotated class gets created.
//Also in most cases this instance is automatically created on application startup.
@Component 
public class ProductDaoService{
	
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
		brands.add(new Brand(1,"nike","Very cool","http/tops.b1"));
		brands.add(new Brand(2,"ellese","Very nice","http/tops.b2"));
		brands.add(new Brand(3,"adidas","Very light","http/tops.b3"));
		brands.add( new Brand(4,"twan","Very well","http/tops.b4"));
		brands.add(new Brand(5,"puma","Very good","http/tops.b5"));
		
	}
	
	private static List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product(1,"SportTop","First", categories.get(0).getID(), categories.get(0).getTitle(),brands.get(0).getID(),brands.get(0).getTitle(), false, true, true, "https://cdn.tamanna.com/assets/foot_locker/nike/317344050080_01.jpg?quality=80&format=auto&width=1000", "white", 19.750));
		
		products.add(new Product(2, "SportTop","Second",  categories.get(1).getID(), categories.get(1).getTitle(), brands.get(1).getID(), brands.get(1).getTitle(), true, false, true, "https://www.yoox.com/images/items/12/12518052WC_14_f.jpg?impolicy=crop&width=387&height=490", "black", 25.0));
		
		products.add(new Product(3, "SportTop","Third",  categories.get(2).getID(),categories.get(2).getTitle(),  brands.get(2).getID(), brands.get(2).getTitle(), true, true, true, "https://adidasm2-cdn.revton.com/media/catalog/product/cache/840x840/1706b311b4e139861e83dd948758a3de/h/3/h37756-10.jpg", "red", 15.0));
		
		products.add(new Product(4,"Classic Top","Fourth",  categories.get(3).getID(), categories.get(3).getTitle(), brands.get(3).getID(), brands.get(3).getTitle(), false, true, true, "https://cdn-images.farfetch-contents.com/15/27/32/97/15273297_27082626_1000.jpg", "pink", 22.0));
		
		products.add(new Product(5,"SportTop","Fifth",  categories.get(0).getID(), categories.get(4).getTitle(), brands.get(0).getID(), brands.get(4).getTitle(), true, true, true,"https://static.aawweb.com/media/catalog/product/cache/9f18371e3a457e456c922dbc54690d4f/1/c/1c03b57c2fd58a611a0f2bc09c97e2fca28e789fee447edec90831ec5a09e899.jpeg", "gray", 10.500));
		
	}

//	@Autowired
//	private ProductRepository productRepository;
	
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
	public List<Product> findByCategory(int category) {
		List<Product> productsCategory = new ArrayList<>(); //list of products of the same category

		for(Product product : products) {
			if( product.getCategory_id() == category){
				productsCategory.add(product);
				}
			}
			return productsCategory;
		}

	// get products upon brand
	public List<Product> findByBrand(int brand_id) {
		List<Product> productsBrand = new ArrayList<>(); //list of products of the same brand
		for(Product product : products) {
			if(product.getBrand_id() == brand_id) {
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
