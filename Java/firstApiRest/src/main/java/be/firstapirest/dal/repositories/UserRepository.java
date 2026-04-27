package be.firstapirest.dal.repositories;

import be.firstapirest.dl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
