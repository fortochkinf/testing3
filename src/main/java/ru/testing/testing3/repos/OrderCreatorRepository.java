package ru.testing.testing3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testing.testing3.domain.OrderCreator;


public interface OrderCreatorRepository extends JpaRepository<OrderCreator, Long> {
}
