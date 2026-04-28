package be.bstorm.tf_java2026_producthell;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TfJava2026ProductHellApplication {

    public static void main(String[] args) {
        Dotenv.load().entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
        SpringApplication.run(TfJava2026ProductHellApplication.class, args);
    }

}
