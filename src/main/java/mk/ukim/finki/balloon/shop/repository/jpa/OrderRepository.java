package mk.ukim.finki.balloon.shop.repository.jpa;

import mk.ukim.finki.balloon.shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    List<Order> findAllByDateCreatedIsGreaterThanAndDateCreatedIsLessThan(LocalDateTime from, LocalDateTime to);
    List<Order> findAllByDateCreatedBetween(LocalDateTime from, LocalDateTime to);

}
