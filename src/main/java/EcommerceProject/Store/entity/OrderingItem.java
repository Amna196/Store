package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@JsonIgnoreProperties(value = { "id" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderingItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String color;
    private BigDecimal price;
    private int quantity;
    private OrderItemStatus status; // = ["In cart", "checkout", "buy", "Sold out"];

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Ordering ordering;

    public enum OrderItemStatus{
        INCART,
        CHECKOUT,
        BUY,
        SOLDOUT;
    }
}
