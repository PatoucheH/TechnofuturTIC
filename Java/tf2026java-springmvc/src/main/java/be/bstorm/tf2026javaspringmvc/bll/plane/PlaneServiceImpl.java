package be.bstorm.tf2026javaspringmvc.bll.plane;

import be.bstorm.tf2026javaspringmvc.bll.aspects.specification.SpecificationParam;
import be.bstorm.tf2026javaspringmvc.bll.aspects.specification.WithSpecification;
import be.bstorm.tf2026javaspringmvc.bll.excpetions.QueryFailedException;
import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneEntity;
import be.bstorm.tf2026javaspringmvc.dal.repositories.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneServiceImpl implements PlaneService{
    private final PlaneRepository planeRepository;

    @WithSpecification(type = PlaneEntity.class, acceptsField = {"imma"})
    @Override
    public List<PlaneEntity> findAll(@SpecificationParam Specification<PlaneEntity> spec) {
        if (spec != null) {
            return planeRepository.findAll(spec);
        }
        return planeRepository.findAll();
    }

    @Override
    public PlaneEntity findByImma(String imma) throws QueryFailedException {
        return planeRepository
                .findByImma(imma)
                .orElseThrow(() -> new QueryFailedException(PlaneEntity.class, "Plane with imma " + imma + " not found"));
    }
}
