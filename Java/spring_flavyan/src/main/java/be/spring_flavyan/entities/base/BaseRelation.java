package be.spring_flavyan.entities.base;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseRelation<T extends Serializable> {
    @EmbeddedId
    private T id;
}
