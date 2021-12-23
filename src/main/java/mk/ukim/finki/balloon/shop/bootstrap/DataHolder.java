package mk.ukim.finki.balloon.shop.bootstrap;

import lombok.Getter;
import mk.ukim.finki.balloon.shop.model.Balloon;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
import mk.ukim.finki.balloon.shop.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();

    @PostConstruct
    public void init() {
        Manufacturer bloons = new Manufacturer("Bloons.inc", "Cambodia", "Sangkat Sala Kamroeuk 98, Siem Reap City");
        manufacturers.add(bloons);
        manufacturers.add(new Manufacturer("Second", "Second", "Second 2, Two"));
        Manufacturer finkib = new Manufacturer("FINKIb", "Macedonia", "Rudzer Boshkovikj 16, Skopje");
        manufacturers.add(finkib);
        manufacturers.add(new Manufacturer("Soder&Co", "Sweden", "Gyllenkrooksgatan 1, Goteborg"));
        Manufacturer alice = new Manufacturer("Alice", "Wonderland", "76th Street 66, New York City");
        manufacturers.add(alice);

        balloons.add(new Balloon("Private", "Penguins balloons", bloons));
        balloons.add(new Balloon("Kowalski", "Penguins balloons", bloons));
        balloons.add(new Balloon("Skipper", "Penguins balloons", bloons));
        balloons.add(new Balloon("Rico", "Penguins balloons", bloons));
        balloons.add(new Balloon("Cake", "Birthday balloons", finkib));
        balloons.add(new Balloon("Candle", "Birthday balloons", finkib));
        balloons.add(new Balloon("Confetti", "Birthday balloons"));
        balloons.add(new Balloon("Santa", "Christmas balloons", alice));
        balloons.add(new Balloon("Snowman", "Christmas balloons", alice));
        balloons.add(new Balloon("Grinch", "Christmas balloons"));
    }

}
