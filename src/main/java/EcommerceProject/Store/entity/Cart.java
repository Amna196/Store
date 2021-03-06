package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private UUID uuid;

    private BigDecimal total;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private String dateTimeCreated;
    private Long userId;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

//    @OneToMany(mappedBy = "cart")
//    private List<Ordering> ordering;

    public Cart(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid == null ? UUID.randomUUID() : uuid;
    }

}
