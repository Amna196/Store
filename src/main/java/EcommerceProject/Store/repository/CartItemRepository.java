package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Cart;
import EcommerceProject.Store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    boolean existsBySku(String sku);

    boolean existsBySkuAndCart(String sku, Cart cart);

    CartItem findBySkuAndCart(String sku, Cart cart);

    boolean existsByCart_id(int id);

    List<CartItem> findByStatusAndCart(CartItem.cartItemStatus checkout, Cart cart);

    boolean existsByStatusAndCart(CartItem.cartItemStatus checkout, Cart cart);

    List<CartItem> findAllByCart_idAndStatusLikeOrStatusLike(int id, CartItem.cartItemStatus incart, CartItem.cartItemStatus soldout);
}
