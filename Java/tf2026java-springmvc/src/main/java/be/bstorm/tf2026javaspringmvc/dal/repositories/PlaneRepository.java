package be.bstorm.tf2026javaspringmvc.dal.repositories;

import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaneRepository extends
        JpaRepository<PlaneEntity, Long>,
        JpaSpecificationExecutor<PlaneEntity>
{

    @Query("SELECT p FROM Plane p WHERE p.imma = :imma")
    Optional<PlaneEntity> findByImma(String imma);
}
