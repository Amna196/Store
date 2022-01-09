
package EcommerceProject.Store.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class PaymentResponse {
    @Id
    @GeneratedValue
    private Long id;

//    @JsonProperty("InvoiceId")
    private Integer invoiceId;

    private Long orderId;

    private PaymentStatus status;

    private BigDecimal total;

    public enum PaymentStatus{
        PENDING,
        PAID,
        CANCELLED;
    }


}
