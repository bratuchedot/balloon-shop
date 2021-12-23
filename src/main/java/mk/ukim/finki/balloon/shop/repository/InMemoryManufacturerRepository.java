package mk.ukim.finki.balloon.shop.repository;

import mk.ukim.finki.balloon.shop.bootstrap.DataHolder;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll() {
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {
        return DataHolder.manufacturers.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    public Optional<Manufacturer> save(String name,
                                       String country,
                                       String address,
                                       LocalDate creationDate) {
        Manufacturer manufacturer = new Manufacturer(name, country, address, creationDate);
        DataHolder.manufacturers.removeIf(b -> b.getName().equals(name));
        DataHolder.manufacturers.add(manufacturer);
        return Optional.of(manufacturer);
    }

}
