package be.firstapirest.pl.dtos.responses;

import be.firstapirest.dal.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {

    private final Long id;
    private final String name;
    private final String category;
    private final String description;
    private final Double price;
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
