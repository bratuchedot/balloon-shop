package mk.ukim.finki.balloon.shop.service;

import mk.ukim.finki.balloon.shop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order placeOrder(String balloonColor, String balloonSize);

    List<Order> listOrders();

    Optional<Order> findById(Long id);

}
