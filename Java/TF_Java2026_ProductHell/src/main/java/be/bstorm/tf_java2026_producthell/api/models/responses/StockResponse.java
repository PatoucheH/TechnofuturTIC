package be.bstorm.tf_java2026_producthell.api.models.responses;

import be.bstorm.tf_java2026_producthell.dl.entities.Stock;
import be.bstorm.tf_java2026_producthell.dl.entities.User;

public record StockResponse(
        Integer id,
        int quantity,
        Integer threshold
) {

    public static StockResponse fromStock(Stock s){
        return new StockResponse(
                s.getId(),
                s.getQuantity(),
                s.getThreshold()
        );
    }
}
