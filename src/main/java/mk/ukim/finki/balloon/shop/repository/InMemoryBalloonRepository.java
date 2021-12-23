package mk.ukim.finki.balloon.shop.repository;

import mk.ukim.finki.balloon.shop.bootstrap.DataHolder;
import mk.ukim.finki.balloon.shop.model.Balloon;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {

    public List<Balloon> findAllBalloons() {
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return DataHolder.balloons.stream()
                .filter(r -> r.getName().contains(text)
                        || r.getDescription().contains(text))
                .collect(Collectors.toList());
    }

}
