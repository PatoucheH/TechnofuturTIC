package be.spring_flavyan.entities.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SoftDelete
public abstract class BaseEntity<T extends Number> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean active = true;
}
