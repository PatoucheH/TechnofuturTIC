package be.firstapirest.bll.services.interfaces;


import be.firstapirest.bll.services.interfaces.base.BaseCRUDService;
import be.firstapirest.dl.entities.Product;

import java.util.List;

public interface ProductService extends BaseCRUDService<Product, Long> {
    List<Product> findByName(String name);
    void update(Long id, Product product);
}
