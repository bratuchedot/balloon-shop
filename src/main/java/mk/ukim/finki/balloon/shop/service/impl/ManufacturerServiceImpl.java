package mk.ukim.finki.balloon.shop.service.impl;

import mk.ukim.finki.balloon.shop.model.Manufacturer;
import mk.ukim.finki.balloon.shop.repository.InMemoryManufacturerRepository;
import mk.ukim.finki.balloon.shop.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final InMemoryManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(InMemoryManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String country, String address, LocalDate creationDate) {
        return manufacturerRepository.save(name, country, address, creationDate);
    }

}
