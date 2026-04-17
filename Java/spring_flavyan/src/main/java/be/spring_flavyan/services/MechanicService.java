package be.spring_flavyan.services;

import be.spring_flavyan.entities.fiscal.Mechanic;
import be.spring_flavyan.repositories.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MechanicService {

    private final MechanicRepository mechanicRepository;

    public List<Mechanic> findAll() {
        List<Mechanic> mechanics =  mechanicRepository.findAll();
        return  mechanics;
    }

    public Mechanic save(Mechanic mechanic){
        mechanicRepository.save(mechanic);
        return mechanic;
    }

    public void delete(Long id) {
        mechanicRepository.deleteById(id);
    }

    public void update(Long id, String name) {
        Mechanic mechanic = mechanicRepository.findById(id).orElseThrow();
        mechanic.setName(name);
        mechanicRepository.save(mechanic);
    }
}
