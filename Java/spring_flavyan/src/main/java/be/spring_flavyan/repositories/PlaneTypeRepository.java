package be.spring_flavyan.repositories;

import be.spring_flavyan.entities.PlaneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneTypeRepository extends JpaRepository<PlaneType, Long> {
}
