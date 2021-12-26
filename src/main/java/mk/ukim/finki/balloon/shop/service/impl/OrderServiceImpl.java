package mk.ukim.finki.balloon.shop.service.impl;

import mk.ukim.finki.balloon.shop.model.Order;
import mk.ukim.finki.balloon.shop.repository.jpa.OrderRepository;
import mk.ukim.finki.balloon.shop.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String balloonColor, String balloonSize, LocalDateTime dateCreated) {
        Order c = new Order(balloonColor, balloonSize, dateCreated);
        orderRepository.save(c);
        return c;
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllByFilterDate(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return orderRepository.findAllByDateCreatedBetween(dateFrom, dateTo);

    }

}
