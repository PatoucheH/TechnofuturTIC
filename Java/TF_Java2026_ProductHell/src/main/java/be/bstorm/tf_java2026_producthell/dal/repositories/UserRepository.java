package be.bstorm.tf_java2026_producthell.dal.repositories;

import be.bstorm.tf_java2026_producthell.dl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u join fetch u.roles where u.username = : username")
    Optional<User> findByUsername(String username);
}
