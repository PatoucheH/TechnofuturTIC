package be.bstorm.tf2026javaspringmvc.dal.specifications;

import be.bstorm.tf2026javaspringmvc.dal.entities.FiscalEntity;
import be.bstorm.tf2026javaspringmvc.dal.entities.PlaneEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public interface PlaneSpecification extends BaseSpecification {

    static Specification<PlaneEntity> startImma(String imma) {
        return (root, query, cb) -> cb.like(root.get("imma"), imma+ "%");
    }
    static Specification<PlaneEntity> containsImma(String imma) {
        return (root, query, cb) -> cb.like(root.get("imma"), "%" + imma + "%");
    }

    static Specification<PlaneEntity> likeOwnerName(String name) {
        return (root, query, cb) -> {
            Join<PlaneEntity, FiscalEntity> ownerJoin = root.join("owner", JoinType.LEFT);
            return cb.like(ownerJoin.get("name"), "%" + name + "%");
        };
    }
}
