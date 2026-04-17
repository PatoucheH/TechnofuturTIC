package be.spring_flavyan.services;

import be.spring_flavyan.entities.fiscal.Pilot;
import be.spring_flavyan.repositories.PilotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PilotService {
    private final PilotRepository pilotRepository;

    public List<Pilot> findAll(){
        List<Pilot> pilots = pilotRepository.findAll();
        return pilots;
    }

    public Pilot save(Pilot pilot){
        pilotRepository.save(pilot);
        return pilot;
    }

    public void delete(Long id) {
        pilotRepository.deleteById(id);
    }

    public void update(Long id, String name, String brevetNumber) {
        Pilot pilot = pilotRepository.findById(id).orElseThrow();
        pilot.setName(name);
        pilot.setBrevetNumber(brevetNumber);
        pilotRepository.save(pilot);
    }
}
