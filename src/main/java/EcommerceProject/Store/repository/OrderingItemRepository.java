package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Ordering;
import EcommerceProject.Store.entity.OrderingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderingItemRepository extends JpaRepository<OrderingItem, Long> {

    List<OrderingItem> findAllByStatus(String checkout);

    List<OrderingItem> findAllByOrdering(Ordering ordering);
}
