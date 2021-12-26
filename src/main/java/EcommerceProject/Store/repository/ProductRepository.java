package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNewProductTrue(Pageable pageable);

    Page<Product> findByFeaturedProductTrue(Pageable pageable);

    Page<Product> findByOrderByPriceAsc(Pageable pageable);

    Page<Product> findByOrderByPriceDesc(Pageable pageable);

    Page<Product> findAllByCategory_title(String categoryTitle, Pageable pageable);

    Page<Product> findByBrand_title(String brandTitle, Pageable pageable);

}
