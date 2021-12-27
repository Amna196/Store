package EcommerceProject.Store.controller;

import EcommerceProject.Store.entity.Product;
import EcommerceProject.Store.repository.ProductRepository;
import EcommerceProject.Store.service.ProductDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductResource {

	@Autowired
	private ProductDaoService service; //for fetching data from lists defined in Dao class

	@Autowired
	private ProductRepository productRepository; //for fetching built-in methods in repository interface extended JpaRepository


   //=================================================================== Fetching data from MYSQL database ============================================================
	@GetMapping(value = "/productPageable") //fetching data from database //1
	public ResponseEntity<Page<Product>> productPageable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		Pageable paging = PageRequest.of(page, size);
		 return ResponseEntity.ok().body(productRepository.findAll(paging));

	}

	@GetMapping("/newProducts") //fetching data from database            //2
	public ResponseEntity<Page<Product>> retrieveNewProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		Pageable paging = PageRequest.of(page, size);
		return ResponseEntity.ok().body(productRepository.findByNewProductTrue(paging));
	}

	@GetMapping("/featuredProducts") //fetching data from database      //3
	public ResponseEntity<Page<Product>> retrieveFeaturedProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		Pageable paging = PageRequest.of(page, size);
		return ResponseEntity.ok().body(productRepository.findByFeaturedProductTrue(paging));
	}

	@GetMapping("/categoryProducts/{categoryTitle}") //fetching data from database   //4
	public ResponseEntity<Page<Product>> retrieveProductsByCategoryTitle(@PathVariable String categoryTitle, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		Pageable paging = PageRequest.of(page, size);
		return ResponseEntity.ok().body(productRepository.findAllByCategory_title(categoryTitle, paging));
	}

	@GetMapping("/brandProducts/{brandTitle}") //fetching data from database          //5
	public ResponseEntity<Page<Product>> retrieveProductsByBrand(@PathVariable String brandTitle, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		Pageable paging = PageRequest.of(page, size);
		return ResponseEntity.ok().body(productRepository.findByBrand_title(brandTitle, paging));
	}

	@GetMapping("/sortProductsAsc")//fetching data from database                    //6
	public ResponseEntity<Page<Product>> retrieveProductsByPriceAsc(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		Pageable paging = PageRequest.of(page, size);
		return ResponseEntity.ok().body(productRepository.findByOrderByPriceAsc(paging));
	}

	@GetMapping("/sortProductsDesc")//fetching data from database                  //7
	public ResponseEntity<Page<Product>> retrieveProductsByPriceDesc(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
		Pageable paging = PageRequest.of(page, size);
		return ResponseEntity.ok().body(productRepository.findByOrderByPriceDesc(paging));
	}

//	@PostMapping("/placeProducts") //posting data to database
//	public void createProduct(@RequestBody Product product){
//		Product savedProduct = productRepository.save(product);
//	}
	//=====================================    Fetching data from lists in Dao class =================================================================

	@GetMapping("/products")//fetching data from products list
	public List<Product> retrieveAllProducts(){
		return service.findAll();
	}

	@GetMapping("/products/new/{New}")//fetching data from products list
	public List<Product> retrieveNewProducts(@PathVariable boolean New){
		return service.findNew(New);
	}

	@GetMapping("/products/featured/{featured}")//fetching data from products list
	public List<Product> retrieveFeaturedProducts(@PathVariable boolean featured){
		return service.findFeatured(featured);
	}

	@GetMapping("/products/category/{category}")
	public List<Product> retrieveProductsByCategory(@PathVariable String category){
		return service.findByCategory(category);
	}


	@GetMapping("/products/brand/{brand}")
	public List<Product> retrieveProductsByBrand(@PathVariable String brand){
		return service.findByBrand(brand);
	}

	@GetMapping("/products/sortByPriceAsc")
	public List<Product> retrieveSortProductsByPriceAsc(){
		List<Product> sortedProducts =  service.sortByPriceAsc();
		return sortedProducts;
	}

	@GetMapping("/products/sortByPriceDesc")
	public List<Product> retrieveSortProductsByPriceDesc(){
		return service.sortByPriceDesc();
	}



}
