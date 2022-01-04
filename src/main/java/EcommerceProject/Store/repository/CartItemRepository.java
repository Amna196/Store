package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Cart;
import EcommerceProject.Store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    boolean existsBySku(String sku);

    boolean existsBySkuAndCart(String sku, Cart cart);

    CartItem findBySkuAndCart(String sku, Cart cart);
}
