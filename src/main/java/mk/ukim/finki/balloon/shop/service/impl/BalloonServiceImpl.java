package mk.ukim.finki.balloon.shop.service.impl;

import mk.ukim.finki.balloon.shop.model.Balloon;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
import mk.ukim.finki.balloon.shop.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.balloon.shop.repository.InMemoryBalloonRepository;
import mk.ukim.finki.balloon.shop.repository.InMemoryManufacturerRepository;
import mk.ukim.finki.balloon.shop.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final InMemoryBalloonRepository inMemoryBalloonRepository;
    private final InMemoryManufacturerRepository inMemoryManufacturerRepository;

    public BalloonServiceImpl(InMemoryBalloonRepository inMemoryBalloonRepository, InMemoryManufacturerRepository inMemoryManufacturerRepository) {
        this.inMemoryBalloonRepository = inMemoryBalloonRepository;
        this.inMemoryManufacturerRepository = inMemoryManufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return inMemoryBalloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return inMemoryBalloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public void deleteById(Long id) {
        inMemoryBalloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return inMemoryBalloonRepository.findById(id);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = inMemoryManufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        return inMemoryBalloonRepository.save(name, description, manufacturer);
    }

    @Override
    public List<Balloon> searchByNameOrManufacturersCountry(String text) {
        return inMemoryBalloonRepository.findAllByNameOrManufacturersCountry(text);
    }

}
