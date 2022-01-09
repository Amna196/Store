package EcommerceProject.Store.repository;

import EcommerceProject.Store.entity.PaymentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentResponseRepository extends JpaRepository<PaymentResponse, Long> {

}
