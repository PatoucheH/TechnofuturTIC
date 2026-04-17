package be.spring_flavyan.repositories;

import be.spring_flavyan.entities.fiscal.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
