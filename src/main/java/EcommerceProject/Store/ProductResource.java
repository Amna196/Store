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

//	// TODO: PAGING GET APP PRODUCTS
//	@GetMapping("/products_pagination")
//	public List<Product> retrieveAllProductsPaging(@PageableDefault(page=0, size=4) Pageable pageRequest){
//		Page<Product> productPage =  service.findAllPagable(pageRequest);
//		productPage.getContent();
//		List<Product> productResultPage = productPage.getContent();
//		productResultPage = productResultPage.stream().map(product -> {
//			converterService.converToDto(product);
//			return product;
//		}).collect(Collectors.toList());
//		return productResultPage;
//	}
	
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

	@GetMapping("/products/sortByPriceAsc")
	public List<Product> retrieveSortProductsByPriceAsc(){
		List<Product> sortedProducts =  service.sortByPriceAsc();
		return sortedProducts;
	}

	@GetMapping("/products/sortByPriceDesc")
	public List<Product> retrieveSortProductsByPriceDesc(){
		return service.sortByPriceDesc();
	}

//	@GetMapping("/products/{pageNo}")
//	public List<Product> getProductList(@PathVariable("pageNo")int pageNo){
//		int pageSize = 2;
//		List<Product> products = service.getProductsList(pageNo-1,pageSize);
//				//.getUserList(pageNo-1,5);
//		return products;
//	}
}
