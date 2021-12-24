package mk.ukim.finki.balloon.shop.service;

import mk.ukim.finki.balloon.shop.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {

    List<Balloon> listAll();

//    Optional<Balloon> searchByNameOrDescription(String text);

    void deleteById(Long id);

    Optional<Balloon> findById(Long id);

    Optional<Balloon> save(String name, String description, Long manufacturerId);

    List<Balloon> searchByNameOrManufacturersCountry(String text);

}
