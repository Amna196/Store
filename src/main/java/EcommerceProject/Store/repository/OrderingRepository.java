package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.Ordering;
import EcommerceProject.Store.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Long> {

    Page<Ordering> findByuuidAndUser(Long uuid, User user, Pageable paging);

    Ordering findByUserAndPaymentStatusLike(User user, Ordering.orderPaymentStatus created);

    Page<Ordering> findAllByUserAndPaymentStatus(User user, Ordering.orderPaymentStatus closed, Pageable paging);
}
