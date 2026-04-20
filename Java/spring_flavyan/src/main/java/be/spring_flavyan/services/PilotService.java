package be.spring_flavyan.services;

import be.spring_flavyan.dtos.request.PilotRequest;
import be.spring_flavyan.dtos.response.PilotResponse;
import be.spring_flavyan.entities.fiscal.Address;
import be.spring_flavyan.entities.fiscal.Pilot;
import be.spring_flavyan.repositories.PilotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PilotService {
    private final PilotRepository pilotRepository;

    public List<PilotResponse> findAll() {
        return pilotRepository.findAll().stream()
                .map(PilotResponse::from)
                .toList();
    }

    public void save(PilotRequest request) {
        pilotRepository.save(request.toEntity());
    }

    public void delete(Long id) {
        pilotRepository.deleteById(id);
    }

    public void update(Long id, String name, String brevetNumber, String street, String city, String zip, String country, String state) {
        Pilot pilot = pilotRepository.findById(id).orElseThrow();
        pilot.setName(name);
        pilot.setBrevetNumber(brevetNumber);
        pilot.setAddress(new Address(street, city, zip, country, state));
        pilotRepository.save(pilot);
    }
}
