package mk.ukim.finki.balloon.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shop_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Convert(converter = UserFullnameConverter.class)
    private String userFullname;

    /**
     * temporal, delete after creation of authentication system
     * that implements spring security
     * */
    private String name;

    private String surname;
    /**********************************************************/

    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

//    TODO: Link users and orders in database
//    @OneToMany(mappedBy = "ofUser", fetch = FetchType.EAGER)
//    private List<Order> orders;

    /**
     * temporal, delete after creation of authentication system
     * that implements spring security
     * */
    public User(String username, String name, String surname, String password, LocalDate dateOfBirth) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
    /**********************************************************/
}
