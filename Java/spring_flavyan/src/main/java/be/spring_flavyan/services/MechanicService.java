package be.spring_flavyan.services;

import be.spring_flavyan.dtos.request.MechanicRequest;
import be.spring_flavyan.dtos.response.MechanicResponse;
import be.spring_flavyan.entities.fiscal.Address;
import be.spring_flavyan.entities.fiscal.Mechanic;
import be.spring_flavyan.repositories.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MechanicService {

    private final MechanicRepository mechanicRepository;

    public List<MechanicResponse> findAll() {
        return mechanicRepository.findAll().stream()
                .map(MechanicResponse::from)
                .toList();
    }

    public void save(MechanicRequest request) {
        mechanicRepository.save(request.toEntity());
    }

    public void delete(Long id) {
        mechanicRepository.deleteById(id);
    }

    public void update(Long id, String name, String street, String city, String zip, String country, String state) {
        Mechanic mechanic = mechanicRepository.findById(id).orElseThrow();
        mechanic.setName(name);
        mechanic.setAddress(new Address(street, city, zip, country, state));
        mechanicRepository.save(mechanic);
    }
}
