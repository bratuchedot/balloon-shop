package mk.ukim.finki.balloon.shop.service.impl;

import mk.ukim.finki.balloon.shop.model.Manufacturer;
import mk.ukim.finki.balloon.shop.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.balloon.shop.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.balloon.shop.service.ManufacturerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
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
    @Transactional
    public Optional<Manufacturer> save(String name, String country, String address, LocalDate creationDate) {
        manufacturerRepository.deleteByName(name);
        return Optional.of(manufacturerRepository.save(new Manufacturer(name, country, address, creationDate)));
    }

}
