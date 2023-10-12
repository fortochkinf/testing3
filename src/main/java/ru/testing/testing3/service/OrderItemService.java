package ru.testing.testing3.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.testing.testing3.domain.Order;
import ru.testing.testing3.domain.OrderItem;
import ru.testing.testing3.model.OrderItemDTO;
import ru.testing.testing3.repos.OrderItemRepository;
import ru.testing.testing3.repos.OrderRepository;
import ru.testing.testing3.util.NotFoundException;


@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderItemService(final OrderItemRepository orderItemRepository,
            final OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderItemDTO> findAll() {
        final List<OrderItem> orderItems = orderItemRepository.findAll(Sort.by("id"));
        return orderItems.stream()
                .map(orderItem -> mapToDTO(orderItem, new OrderItemDTO()))
                .toList();
    }

    public OrderItemDTO get(final Long id) {
        return orderItemRepository.findById(id)
                .map(orderItem -> mapToDTO(orderItem, new OrderItemDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrderItemDTO orderItemDTO) {
        final OrderItem orderItem = new OrderItem();
        mapToEntity(orderItemDTO, orderItem);
        return orderItemRepository.save(orderItem).getId();
    }

    public void update(final Long id, final OrderItemDTO orderItemDTO) {
        final OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderItemDTO, orderItem);
        orderItemRepository.save(orderItem);
    }

    public void delete(final Long id) {
        orderItemRepository.deleteById(id);
    }

    private OrderItemDTO mapToDTO(final OrderItem orderItem, final OrderItemDTO orderItemDTO) {
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setName(orderItem.getName());
        orderItemDTO.setDescription(orderItem.getDescription());
        orderItemDTO.setOrder(orderItem.getOrder() == null ? null : orderItem.getOrder().getId());
        return orderItemDTO;
    }

    private OrderItem mapToEntity(final OrderItemDTO orderItemDTO, final OrderItem orderItem) {
        orderItem.setName(orderItemDTO.getName());
        orderItem.setDescription(orderItemDTO.getDescription());
        final Order order = orderItemDTO.getOrder() == null ? null : orderRepository.findById(orderItemDTO.getOrder())
                .orElseThrow(() -> new NotFoundException("order not found"));
        orderItem.setOrder(order);
        return orderItem;
    }

}
