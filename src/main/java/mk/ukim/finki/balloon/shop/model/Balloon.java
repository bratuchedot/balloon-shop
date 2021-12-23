package mk.ukim.finki.balloon.shop.model;

import lombok.Data;

@Data
public class Balloon {

    private Long id;

    private String name;

    private String description;

    private Manufacturer manufacturer;

    public Balloon(String name, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
    }

    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

}
