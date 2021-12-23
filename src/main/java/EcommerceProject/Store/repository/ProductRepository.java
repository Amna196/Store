package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{ //JpaRepository<Product,Long> {

}
