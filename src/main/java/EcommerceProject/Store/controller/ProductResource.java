package EcommerceProject.Store.controller;

import EcommerceProject.Store.entity.Product;
import EcommerceProject.Store.repository.ProductRepository;
import EcommerceProject.Store.service.ProductDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductResource {

	@Autowired
	private ProductDaoService service;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/products")
	public List<Product> retrieveAllProducts(){
		return service.findAll();
	}

	@GetMapping(value = "/productPageable")
	public Page productPageable(Pageable pageable){
		return productRepository.findAll(pageable);
	}

	@GetMapping("/products/new/{New}")
	public List<Product> retrieveNewProducts(@PathVariable boolean New){
		return service.findNew(New);
	}


	
	@GetMapping("/products/featured/{featured}")
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
