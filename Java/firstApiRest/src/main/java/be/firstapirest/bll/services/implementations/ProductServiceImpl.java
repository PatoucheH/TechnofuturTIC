package be.firstapirest.bll.services.implementations;

import be.firstapirest.bll.services.implementations.base.AbstractCRUDImpl;
import be.firstapirest.bll.services.interfaces.ProductService;
import be.firstapirest.dl.entities.Product;
import be.firstapirest.dal.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends AbstractCRUDImpl<Product, Long, ProductRepository>
                                implements ProductService {

    public ProductServiceImpl(ProductRepository repo) {
        super(repo);
    }

    @Override
    public List<Product> findByName(String name){
        return repository.findProductByNameContaining(name);
    }

    @Override
    public void update(Long id, Product product){
        Product productToUpdate = findById(id);
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setStock(product.getStock());
        repository.save(productToUpdate);
    }
}
