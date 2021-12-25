package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNewProductTrue();
    List<Product> findByFeaturedProductTrue();
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();
//    List<Product> findCategoryByTitle(String categoryTitle);

//    List<Product> findCategoryByID(String categoryID);

//    List<Product> findCategoryByiD(int categoryID);

//    List<Product> findAllCategoryByiD(int categoryID);

    List<Product> findAllByCategoryiD(int categoryID);
}
