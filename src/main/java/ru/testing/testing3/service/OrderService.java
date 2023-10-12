package ru.testing.testing3.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.testing.testing3.domain.Order;
import ru.testing.testing3.domain.OrderCreator;
import ru.testing.testing3.model.OrderDTO;
import ru.testing.testing3.repos.OrderCreatorRepository;
import ru.testing.testing3.repos.OrderRepository;
import ru.testing.testing3.util.NotFoundException;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderCreatorRepository orderCreatorRepository;

    public OrderService(final OrderRepository orderRepository,
            final OrderCreatorRepository orderCreatorRepository) {
        this.orderRepository = orderRepository;
        this.orderCreatorRepository = orderCreatorRepository;
    }

    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findAll(Sort.by("id"));
        return orders.stream()
                .map(order -> mapToDTO(order, new OrderDTO()))
                .toList();
    }

    public OrderDTO get(final Long id) {
        return orderRepository.findById(id)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getId();
    }

    public void update(final Long id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setComment(order.getComment());
        orderDTO.setCreator(order.getCreator() == null ? null : order.getCreator().getId());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        order.setComment(orderDTO.getComment());
        final OrderCreator creator = orderDTO.getCreator() == null ? null : orderCreatorRepository.findById(orderDTO.getCreator())
                .orElseThrow(() -> new NotFoundException("creator not found"));
        order.setCreator(creator);
        return order;
    }

}
