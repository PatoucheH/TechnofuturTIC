package be.bstorm.tf_java2026_producthell.il.utils;

import be.bstorm.tf_java2026_producthell.dal.repositories.ProductRepository;
import be.bstorm.tf_java2026_producthell.dal.repositories.UserRepository;
import be.bstorm.tf_java2026_producthell.dl.entities.Category;
import be.bstorm.tf_java2026_producthell.dl.entities.Product;
import be.bstorm.tf_java2026_producthell.dl.entities.Stock;
import be.bstorm.tf_java2026_producthell.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.count()==0){
            userRepository.save(new User("admin@admin.be", "admin","admin"));
        }


        Category firstCategory = new Category("Moto1");

        if(productRepository.count() == 0) {
            List<Product> products = List.of(
                    new Product(
                            "Rebel",
                            800000,
                            firstCategory,
                            new Stock(5, 10)
                    ),
                    new Product(
                            "Vulcan",
                            800000,
                            firstCategory,
                            new Stock(0, 10)
                    ),
                    new Product(
                            "Ninja",
                            800000,
                            firstCategory,
                            new Stock(2, 10)
                    )
            );

            productRepository.saveAll(products);
        }
    }
}