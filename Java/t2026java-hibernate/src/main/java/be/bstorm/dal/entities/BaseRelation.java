package be.bstorm.dal.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseRelation<T extends Serializable> {
    @EmbeddedId
    private T id;
}
