package mk.ukim.finki.balloon.shop.bootstrap;

import lombok.Getter;
import mk.ukim.finki.balloon.shop.model.Balloon;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();

    @PostConstruct
    public void init() {
        balloons.add(new Balloon("Private", "Penguins balloons"));
        balloons.add(new Balloon("Kowalski", "Penguins balloons"));
        balloons.add(new Balloon("Skipper", "Penguins balloons"));
        balloons.add(new Balloon("Rico", "Penguins balloons"));
        balloons.add(new Balloon("Cake", "Birthday balloons"));
        balloons.add(new Balloon("Candle", "Birthday balloons"));
        balloons.add(new Balloon("Confetti", "Birthday balloons"));
        balloons.add(new Balloon("Santa", "Christmas balloons"));
        balloons.add(new Balloon("Snowman", "Christmas balloons"));
        balloons.add(new Balloon("Grinch", "Christmas balloons"));
    }

}
