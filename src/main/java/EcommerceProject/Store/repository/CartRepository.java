package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {


//    boolean existsByUUID(int uuid);

//    boolean findByUserId_username(String username);

//    boolean findByuserId_username(String username);

    Cart findByuserId(Long id);
}
