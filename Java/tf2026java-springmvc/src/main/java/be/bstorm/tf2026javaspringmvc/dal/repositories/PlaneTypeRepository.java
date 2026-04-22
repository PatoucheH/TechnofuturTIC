package be.bstorm.tf2026javaspringmvc.dal.repositories;

import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneTypeRepository extends JpaRepository<PlaneTypeEntity, Long> {
}
