package mk.ukim.finki.balloon.shop.repository;

import mk.ukim.finki.balloon.shop.bootstrap.DataHolder;
import mk.ukim.finki.balloon.shop.model.Balloon;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
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

    public void deleteById(long id) {
        DataHolder.balloons.removeIf(b -> b.getId() == id);
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloons.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer) {
        Balloon balloon = new Balloon(name, description, manufacturer);
        DataHolder.balloons.removeIf(b -> b.getName().equals(name));
        DataHolder.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public List<Balloon> findAllByNameOrManufacturersCountry(String text) {
        return DataHolder.balloons.stream()
                .filter(r -> r.getName().contains(text)
                        || ((r.getManufacturer() != null) ? r.getManufacturer().getCountry().contains(text) : r.getName().contains(text)))
                .collect(Collectors.toList());
    }

}
