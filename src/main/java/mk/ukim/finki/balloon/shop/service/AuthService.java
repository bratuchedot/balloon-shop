package mk.ukim.finki.balloon.shop.service;

import mk.ukim.finki.balloon.shop.model.User;

import java.time.LocalDate;

public interface AuthService {

    User login(String username, String password);

    User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth);

}
