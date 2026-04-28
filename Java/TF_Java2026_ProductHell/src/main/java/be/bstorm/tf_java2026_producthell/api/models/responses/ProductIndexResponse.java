package be.bstorm.tf_java2026_producthell.api.models.responses;

import be.bstorm.tf_java2026_producthell.dl.entities.Product;

import java.util.UUID;

public record ProductIndexResponse(
        UUID id,
        String name,
        int price,
        String categoryName
) {

    public static ProductIndexResponse fromProduct(Product p){
        return new ProductIndexResponse(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getCategory().getName()
        );
    }
}