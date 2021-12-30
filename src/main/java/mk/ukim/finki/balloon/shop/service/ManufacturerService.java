package mk.ukim.finki.balloon.shop.service;

import mk.ukim.finki.balloon.shop.model.Manufacturer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> save(String name, String country, String address, LocalDate creationDate);

    Optional<Manufacturer> edit(Long id, String name, String country, String address, LocalDate creationDate);


}
