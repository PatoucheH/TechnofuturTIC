package be.firstapirest.api.dtos.responses;

import be.firstapirest.dl.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductResponse {

    private final UUID id;
    private final String name;
    private final String category;
    private final String description;
    private final Long price;
    private final Integer quantity;

    public static ProductResponse from(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getCategory().getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStock().getQuantity()
        );
    }

}
