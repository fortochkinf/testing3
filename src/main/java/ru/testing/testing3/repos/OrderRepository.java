package ru.testing.testing3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testing.testing3.domain.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
