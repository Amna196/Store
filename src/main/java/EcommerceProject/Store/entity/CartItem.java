package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Cart cart;
    @OneToMany
    private List<ProductVariant> productVariants;
    private BigDecimal price;
    private int nou;//Number Of Unit
    private Boolean active;



}
