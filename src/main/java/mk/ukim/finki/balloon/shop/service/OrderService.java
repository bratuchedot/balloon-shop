package mk.ukim.finki.balloon.shop.service;

import mk.ukim.finki.balloon.shop.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order placeOrder(String balloonColor, String balloonSize, LocalDateTime dateCreated);

    List<Order> listOrders();

    Optional<Order> findById(Long id);

    List<Order> findAllByFilterDate(LocalDateTime dateFrom, LocalDateTime dateTo);

}
