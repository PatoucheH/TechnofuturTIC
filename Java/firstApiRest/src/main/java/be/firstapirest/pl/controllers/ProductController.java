package be.firstapirest.pl.controllers;

import be.firstapirest.bll.services.interfaces.ProductService;
import be.firstapirest.pl.dtos.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> findAll(){
        return productService.findAll().stream()
                .map(ProductResponse::from)
                .toList();
    }
}
