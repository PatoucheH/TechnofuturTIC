package be.bstorm.tf_java2026_producthell.api.controllers;

import be.bstorm.tf_java2026_producthell.api.models.CustomPage;
import be.bstorm.tf_java2026_producthell.api.models.responses.ProductIndexResponse;
import be.bstorm.tf_java2026_producthell.api.models.responses.StockResponse;
import be.bstorm.tf_java2026_producthell.bll.services.ProductService;
import be.bstorm.tf_java2026_producthell.dl.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<CustomPage<ProductIndexResponse>> get(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        Page<Product> productPage = productService.findProduct(PageRequest.of(page,pageSize));
        CustomPage<ProductIndexResponse> response = new CustomPage<ProductIndexResponse>(
                productPage.getContent().stream()
                        .map(ProductIndexResponse::fromProduct)
                        .toList(),
                productPage.getNumber(),
                productPage.getTotalPages(),
                productPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductIndexResponse> save(@RequestBody Product product){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.save(product));
    }

    @GetMapping("/stock")
    public ResponseEntity<StockResponse> getStockByProduct(@RequestParam UUID productId){
        Product p = productService.findById(productId);
        return ResponseEntity.ok().body(productService.findStockByProduct(p));
    }
}
