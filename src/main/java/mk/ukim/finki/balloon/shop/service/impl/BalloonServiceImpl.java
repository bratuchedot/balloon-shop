package mk.ukim.finki.balloon.shop.service.impl;

import mk.ukim.finki.balloon.shop.model.Balloon;
import mk.ukim.finki.balloon.shop.model.Manufacturer;
import mk.ukim.finki.balloon.shop.model.exceptions.BalloonNotFoundException;
import mk.ukim.finki.balloon.shop.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.balloon.shop.repository.jpa.BalloonRepository;
import mk.ukim.finki.balloon.shop.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.balloon.shop.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

//    @Override
//    public Optional<Balloon> searchByNameOrDescription(String text) {
//        return balloonRepository.findByNameOrDescription(text);
//    }

    @Override
    public void deleteById(Long id) {
        balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        balloonRepository.deleteByName(name);
        return Optional.of(balloonRepository.save(new Balloon(name, description, manufacturer)));
    }

    @Override
    @Transactional
    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturerId) {
        Balloon balloon = balloonRepository.findById(id)
                .orElseThrow(() -> new BalloonNotFoundException(id));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        balloon.setName(name);
        balloon.setDescription(description);
        balloon.setManufacturer(manufacturer);
        return Optional.of(balloonRepository.save(balloon));
    }

    @Override
    public List<Balloon> searchByNameOrManufacturersCountry(String text) {
        return balloonRepository.findAllByNameOrManufacturer_Country(text, text);
    }

}
