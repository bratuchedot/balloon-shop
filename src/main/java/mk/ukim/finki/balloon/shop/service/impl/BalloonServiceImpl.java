package mk.ukim.finki.balloon.shop.service.impl;

import mk.ukim.finki.balloon.shop.model.Balloon;
import mk.ukim.finki.balloon.shop.repository.InMemoryBalloonRepository;
import mk.ukim.finki.balloon.shop.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final InMemoryBalloonRepository inMemoryBalloonRepository;

    public BalloonServiceImpl(InMemoryBalloonRepository inMemoryBalloonRepository) {
        this.inMemoryBalloonRepository = inMemoryBalloonRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return inMemoryBalloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return inMemoryBalloonRepository.findAllByNameOrDescription(text);
    }

}
