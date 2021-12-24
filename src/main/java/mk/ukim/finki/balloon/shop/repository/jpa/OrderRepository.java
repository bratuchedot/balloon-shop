package mk.ukim.finki.balloon.shop.repository.jpa;

import mk.ukim.finki.balloon.shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
