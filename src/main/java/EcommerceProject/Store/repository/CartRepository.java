package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {


//    boolean existsByUUID(int uuid);

    Cart findByuserId(Long id);

    Page<Cart> findByuserId(Long id, Pageable paging);
}
