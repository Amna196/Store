package EcommerceProject.Store.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 20)
    private String password;

    @Email
    private String email;
//    @OneToOne(fetch=FetchType.LAZY)
//    @JsonIgnore
//    private Cart cart;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


}
