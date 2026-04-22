package be.bstorm.tf2026javaspringmvc.bll.plane;

import be.bstorm.tf2026javaspringmvc.bll.excpetions.QueryFailedException;
import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PlaneService {
    List<PlaneEntity> findAll(Specification<PlaneEntity> spec);
    PlaneEntity findByImma(String imma) throws QueryFailedException;
}
