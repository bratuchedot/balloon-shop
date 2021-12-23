package mk.ukim.finki.balloon.shop.service;

import mk.ukim.finki.balloon.shop.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(Long id);

}
