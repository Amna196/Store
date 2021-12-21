package EcommerceProject.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductResource {

	@Autowired
	private ProductDaoService service;

	@GetMapping("/products")
	public List<Product> retrieveAllProducts(){
		return service.findAll();
	}
	
	@GetMapping("/products/new/{New}")
	public List<Product> retrieveNewProducts(@PathVariable boolean New){
		return service.findNew(New);
	}
	
	@GetMapping("/products/featured/{featured}")
	public List<Product> retrieveFeaturedProducts(@PathVariable boolean featured){
		return service.findFeatured(featured);
	}

	@GetMapping("/products/category_id/{category_id}")
	public List<Product> retrieveProductsByCategory(@PathVariable int category_id){
		return service.findByCategory(category_id);
	}

	@GetMapping("/products/brand_id/{brand_id}")
	public List<Product> retrieveProductsByBrand(@PathVariable int brand_id){
		return service.findByBrand(brand_id);
	}

	//TODO: SortProductsPriceInAsc
	@GetMapping("/products/sortByPriceAsc")
	public List<Product> retrieveSortProductsByPriceAsc(){
		List<Product> sortedProducts =  service.sortByPriceAsc();
		return sortedProducts;
	}

	//TODO: SortProductsPriceInDesc
	@GetMapping("/products/sortByPriceDesc")
	public List<Product> retrieveSortProductsByPriceDesc(){
		return service.sortByPriceDesc();
	}
}
