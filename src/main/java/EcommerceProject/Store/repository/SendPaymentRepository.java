package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.SendPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendPaymentRepository extends JpaRepository<SendPayment, Long> {

}
