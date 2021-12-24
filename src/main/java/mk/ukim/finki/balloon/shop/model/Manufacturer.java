package mk.ukim.finki.balloon.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "man_name")
    private String name;

    @Column(name = "man_country")
    private String country;

    @Column(name = "man_address")
    private String address;

    @Column(name = "man_creation_date")
    private LocalDate creationDate;

    public Manufacturer(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Manufacturer(String name, String country, String address, LocalDate creationDate) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.creationDate = creationDate;
    }
}
