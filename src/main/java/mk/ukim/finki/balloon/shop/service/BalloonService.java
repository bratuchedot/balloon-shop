package mk.ukim.finki.balloon.shop.service;

import mk.ukim.finki.balloon.shop.model.Balloon;

import java.util.List;

public interface BalloonService {

    List<Balloon> listAll();

    List<Balloon> searchByNameOrDescription(String text);

}
