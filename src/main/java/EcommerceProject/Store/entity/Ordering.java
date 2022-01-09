package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(value = { "uuid" })
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
    private BigDecimal grandTotal;// = itemsTotal.add(shippingTotal);
    private String dateTimeCreated;

    @OneToMany(mappedBy = "ordering")
    private List<OrderingItem> orderingItems; // get checkout items only

    @JsonProperty("InvoiceURL")
    private String paymentLink;

    public enum orderPaymentStatus{
        CREATED,
        CHECKOUT,
        CLOSED;
    }

//    public UUID getUuid() {
//        return uuid == null ? UUID.randomUUID() : uuid;
//    }

}
