package ru.testing.testing3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testing.testing3.domain.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
