package be.firstapirest.dal.repositories;

import be.firstapirest.dal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
