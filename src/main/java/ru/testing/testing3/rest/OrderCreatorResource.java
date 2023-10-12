package ru.testing.testing3.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testing.testing3.model.OrderCreatorDTO;
import ru.testing.testing3.service.OrderCreatorService;


@RestController
@RequestMapping(value = "/api/orderCreators", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderCreatorResource {

    private final OrderCreatorService orderCreatorService;

    public OrderCreatorResource(final OrderCreatorService orderCreatorService) {
        this.orderCreatorService = orderCreatorService;
    }

    @GetMapping
    public ResponseEntity<List<OrderCreatorDTO>> getAllOrderCreators() {
        return ResponseEntity.ok(orderCreatorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderCreatorDTO> getOrderCreator(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(orderCreatorService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrderCreator(
            @RequestBody @Valid final OrderCreatorDTO orderCreatorDTO) {
        final Long createdId = orderCreatorService.create(orderCreatorDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateOrderCreator(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final OrderCreatorDTO orderCreatorDTO) {
        orderCreatorService.update(id, orderCreatorDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrderCreator(@PathVariable(name = "id") final Long id) {
        orderCreatorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
