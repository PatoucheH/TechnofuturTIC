package be.bstorm.tf_java2026_producthell.api.controllers;

import be.bstorm.tf_java2026_producthell.api.models.requests.OrderRequest;
import be.bstorm.tf_java2026_producthell.api.models.responses.OrderResponse;
import be.bstorm.tf_java2026_producthell.bll.services.OrderService;
import be.bstorm.tf_java2026_producthell.dl.enums.OrderStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }


    @PostMapping
    public ResponseEntity<List<OrderResponse>> placeOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.placeOrder(request));
    }

    @PatchMapping("/{id}/changeStatus")
    public ResponseEntity<?> changeOrderStatus(@PathVariable UUID id, @RequestBody OrderStatus orderStatus) {
        orderService.changeOrderStatus(id, orderStatus);
        return ResponseEntity.noContent().build();
    }
}
