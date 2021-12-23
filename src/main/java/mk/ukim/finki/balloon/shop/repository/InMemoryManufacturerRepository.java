package mk.ukim.finki.balloon.shop.repository;

import mk.ukim.finki.balloon.shop.bootstrap.DataHolder;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
import org.springframework.stereotype.Repository;

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

}
