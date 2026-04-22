package be.bstorm.tf2026javaspringmvc.dal.repositories;

import be.bstorm.tf2026javaspringmvc.dal.entities.FiscalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiscalRepository extends JpaRepository<FiscalEntity, Long> {
}
