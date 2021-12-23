package mk.ukim.finki.balloon.shop.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Manufacturer {

    private Long id;

    private String name;

    private String country;

    private String address;

    private LocalDate creationDate;

    public Manufacturer(String name, String country, String address) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Manufacturer(String name, String country, String address, LocalDate creationDate) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.country = country;
        this.address = address;
        this.creationDate = creationDate;
    }
}
