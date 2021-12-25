package EcommerceProject.Store.controller;

import EcommerceProject.Store.entity.Product;
import EcommerceProject.Store.repository.ProductRepository;
import EcommerceProject.Store.service.ProductDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductResource {

	@Autowired
	private ProductDaoService service;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/products")//fetching data from products list
	public List<Product> retrieveAllProducts(){
		return service.findAll();
	}

	@GetMapping(value = "/productPageable") //fetching data from database
	public Page<Product> productPageable(Pageable pageable){
		return productRepository.findAll(pageable);
	}

	@GetMapping("/products/new/{New}")//fetching data from products list
	public List<Product> retrieveNewProducts(@PathVariable boolean New){
		return service.findNew(New);
	}

	@GetMapping("/newProducts") //fetching data from database
	public List<Product> retrieveNewProducts(){
		return productRepository.findByNewProductTrue();
	}

	@GetMapping("/products/featured/{featured}")//fetching data from products list
	public List<Product> retrieveFeaturedProducts(@PathVariable boolean featured){
		return service.findFeatured(featured);
	}

	@GetMapping("/featuredProducts") //fetching data from database
	public List<Product> retrieveFeaturedProducts(){
		return productRepository.findByFeaturedProductTrue();
	}

	@GetMapping("/products/category/{category}")
	public List<Product> retrieveProductsByCategory(@PathVariable String category){
		return service.findByCategory(category);
	}

	@GetMapping("/categoryProducts/{categoryID}") //fetching data from database
	public List<Product> retrieveProductsByCategoryTitle(@PathVariable int categoryID){
		List<Product> category = productRepository.findAllByCategoryiD(categoryID);
		return category;
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

	@GetMapping("/sortProductsAsc")//fetching data from database
	public List<Product> retrieveProductsByPriceAsc(){
		return productRepository.findByOrderByPriceAsc();
	}

	@GetMapping("/products/sortByPriceDesc")
	public List<Product> retrieveSortProductsByPriceDesc(){
		return service.sortByPriceDesc();
	}

	@GetMapping("/sortProductsDesc")//fetching data from database
	public List<Product> retrieveProductsByPriceDesc(){
		return productRepository.findByOrderByPriceDesc();
	}

	//TODO: CREATE PRODUCT
	@PostMapping("/products")
	public void createProduct(@RequestBody Product product){
		Product savedProduct = service.save(product);
	}


////	@GetMapping("/products/{pageNo}")
//	@RequestMapping(value = "/listPageable", method = RequestMethod.GET)
//	public Page<Product> getProductList(Pageable pageable){
//		int pageSize = 2;
//		return  productRepository.findAll(pageable);//getProductsList(pageNo-1,pageSize);
//
//	}

//	@GetMapping("/productss")
//	public String listAll(Model model){
//		List<Product> listProducts = productRepository.findAll();
//		model.addAttribute("listProducts", listProducts);
//		return  "products";
//
//	}
}
