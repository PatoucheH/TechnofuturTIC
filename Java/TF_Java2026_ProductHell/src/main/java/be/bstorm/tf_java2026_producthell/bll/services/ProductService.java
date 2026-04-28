package be.bstorm.tf_java2026_producthell.bll.services;

import be.bstorm.tf_java2026_producthell.api.models.responses.ProductIndexResponse;
import be.bstorm.tf_java2026_producthell.api.models.responses.StockResponse;
import be.bstorm.tf_java2026_producthell.dal.repositories.ProductRepository;
import be.bstorm.tf_java2026_producthell.dl.entities.Product;
import be.bstorm.tf_java2026_producthell.dl.entities.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findProduct(Pageable pageable) {

        return productRepository.findProductsPageWithCategory(pageable);
    }

    public Product findById(UUID productId){
        return productRepository.findById(productId).orElseThrow();
    }

    public StockResponse findStockByProduct(Product product) {
        return StockResponse.fromStock(product.getStock());
    }

    public ProductIndexResponse save(Product product){
        productRepository.save(product);
        return ProductIndexResponse.fromProduct(product);
    }
}