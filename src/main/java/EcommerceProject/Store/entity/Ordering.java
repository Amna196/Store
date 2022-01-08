package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ordering {

    @Id
    @GeneratedValue
    private Long uuid;

    @ManyToOne(fetch= FetchType.EAGER)
    @JsonIgnore
    private User user;

    private orderPaymentStatus paymentStatus; //['created', 'checkout', 'closed']
    private BigDecimal itemsTotal;
    private BigDecimal shippingTotal; // = BigDecimal.valueOf(3);
    private BigDecimal grindTotal;// = itemsTotal.add(shippingTotal);
    private String dateTimeCreated;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonIgnore
//    private Cart cart;

        // map cartItems to orderItem
    @OneToMany(mappedBy = "ordering")
    private List<OrderingItem> orderingItems; // get checkout items only

    public enum orderPaymentStatus{
        CREATED,
        CHECKOUT,
        CLOSED;
    }

//    public UUID getUuid() {
//        return uuid == null ? UUID.randomUUID() : uuid;
//    }

}
