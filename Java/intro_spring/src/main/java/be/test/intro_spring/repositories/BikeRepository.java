package be.test.intro_spring.repositories;

import be.test.intro_spring.entities.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {
    @Query("select b from Bike b where lower(b.brand) like %:brand%")
    List<Bike> findByBrandContaining(String brand);

    @Query("""
            SELECT b FROM Bike b
            WHERE (:brand IS NULL OR LOWER(b.brand) LIKE %:brand%)
            AND (:minPower IS NULL OR b.power >= :minPower)
            AND (:maxPower IS NULL OR b.power <= :maxPower)
            """)
    List<Bike> search(String brand, Integer minPower, Integer maxPower);
}
