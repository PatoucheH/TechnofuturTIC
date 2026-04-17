package be.spring_flavyan.repositories;

import be.spring_flavyan.entities.fiscal.Fiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiscalRepository extends JpaRepository<Fiscal, Long> {

    @Query("SELECT f FROM Fiscal f WHERE TYPE(f) = Fiscal")
    List<Fiscal> findBaseFiscalsOnly();
}
