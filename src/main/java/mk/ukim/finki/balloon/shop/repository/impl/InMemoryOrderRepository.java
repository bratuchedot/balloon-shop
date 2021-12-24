package mk.ukim.finki.balloon.shop.repository.impl;

import mk.ukim.finki.balloon.shop.bootstrap.DataHolder;
import mk.ukim.finki.balloon.shop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryOrderRepository {

    public List<Order> findAll() {
        return DataHolder.orders;
    }

    public Optional<Order> findById(Long id) {
        return DataHolder.orders.stream()
                .filter(o -> o.getOrderId().equals(id))
                .findFirst();
    }

    public Order save(Order o) {
        DataHolder.orders.add(o);
        return o;
    }

}
