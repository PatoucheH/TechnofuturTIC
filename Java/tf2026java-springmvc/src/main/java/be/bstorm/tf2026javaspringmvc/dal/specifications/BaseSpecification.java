package be.bstorm.tf2026javaspringmvc.dal.specifications;

import org.springframework.data.jpa.domain.Specification;

public interface BaseSpecification{
    static <T> Specification<T> isActive() {
        return (root, query, cb) -> cb.equal(root.get("active"), true);
    }
}
