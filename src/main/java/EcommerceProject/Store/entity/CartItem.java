package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JsonIgnore
    private Cart cart;
    private String sku;
    private BigDecimal price;

    private int nou;//Number Of Unit

    private Boolean active;

    private cartItemStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Product product;

    public enum cartItemStatus{
        INCART,
        CHECKOUT,
        BUY,
        SOLDOUT;
    }; // = ["In cart", "checkout", "buy", "Sold out"];



}
