package mk.ukim.finki.balloon.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

//    TODO: Link users and orders in database
//    @ManyToOne
//    private User ofUser;

    private String balloonColor;

    private String balloonSize;

    private LocalDateTime dateCreated;

    public Order(String balloonColor, String balloonSize, LocalDateTime dateCreated) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.dateCreated = dateCreated;
    }

}
