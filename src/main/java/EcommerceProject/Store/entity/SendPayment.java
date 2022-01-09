package EcommerceProject.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.math.BigDecimal;

@JsonIgnoreProperties(value = { "id" })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SendPayment {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("CustomerName")
    private String username;

    @JsonProperty("NotificationOption")
    private String notification;

    @Email
    @JsonProperty("CustomerEmail")
    private String email;

    @JsonProperty("CustomerMobile")
    private String phoneNumber;

    @JsonProperty("InvoiceValue")
    private BigDecimal total;
}
