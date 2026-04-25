package be.firstapirest.dal.repositories;

import be.firstapirest.dal.entities.CommandLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandLineRepository extends JpaRepository<CommandLine, Long> {
}
