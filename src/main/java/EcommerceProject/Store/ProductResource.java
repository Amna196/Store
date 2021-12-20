package EcommerceProject.Store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
//	//TODO: retrieveProductsByCategory
//	@GetMapping("/products/category/{category_title}") 
//	public List<Product> retrieveProductsByCategory(@PathVariable Category category_title){
//		return service.findByCategory(category_title);
//	}
//	
//	//TODO: retrieveProductsByBrand
//	@GetMapping("/products/brand/{brand}") 
//	public List<Product> retrieveProductsByBrand(@PathVariable String brand){
//		return service.findByBrand(brand);
//	}
}
