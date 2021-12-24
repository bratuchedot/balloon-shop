package mk.ukim.finki.balloon.shop.repository.jpa;

import mk.ukim.finki.balloon.shop.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    void deleteByName(String name);
}
