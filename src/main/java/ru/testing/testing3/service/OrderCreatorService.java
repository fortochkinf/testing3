package ru.testing.testing3.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.testing.testing3.domain.OrderCreator;
import ru.testing.testing3.model.OrderCreatorDTO;
import ru.testing.testing3.repos.OrderCreatorRepository;
import ru.testing.testing3.util.NotFoundException;


@Service
public class OrderCreatorService {

    private final OrderCreatorRepository orderCreatorRepository;

    public OrderCreatorService(final OrderCreatorRepository orderCreatorRepository) {
        this.orderCreatorRepository = orderCreatorRepository;
    }

    public List<OrderCreatorDTO> findAll() {
        final List<OrderCreator> orderCreators = orderCreatorRepository.findAll(Sort.by("id"));
        return orderCreators.stream()
                .map(orderCreator -> mapToDTO(orderCreator, new OrderCreatorDTO()))
                .toList();
    }

    public OrderCreatorDTO get(final Long id) {
        return orderCreatorRepository.findById(id)
                .map(orderCreator -> mapToDTO(orderCreator, new OrderCreatorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrderCreatorDTO orderCreatorDTO) {
        final OrderCreator orderCreator = new OrderCreator();
        mapToEntity(orderCreatorDTO, orderCreator);
        return orderCreatorRepository.save(orderCreator).getId();
    }

    public void update(final Long id, final OrderCreatorDTO orderCreatorDTO) {
        final OrderCreator orderCreator = orderCreatorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderCreatorDTO, orderCreator);
        orderCreatorRepository.save(orderCreator);
    }

    public void delete(final Long id) {
        orderCreatorRepository.deleteById(id);
    }

    private OrderCreatorDTO mapToDTO(final OrderCreator orderCreator,
            final OrderCreatorDTO orderCreatorDTO) {
        orderCreatorDTO.setId(orderCreator.getId());
        orderCreatorDTO.setUserName(orderCreator.getUserName());
        return orderCreatorDTO;
    }

    private OrderCreator mapToEntity(final OrderCreatorDTO orderCreatorDTO,
            final OrderCreator orderCreator) {
        orderCreator.setUserName(orderCreatorDTO.getUserName());
        return orderCreator;
    }

}
