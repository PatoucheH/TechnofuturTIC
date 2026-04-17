package be.spring_flavyan.services;

import be.spring_flavyan.entities.PlaneType;
import be.spring_flavyan.repositories.PlaneTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneTypeService {

    private final PlaneTypeRepository planeTypeRepository;

    public List<PlaneType> findAll() {
        List<PlaneType> planeTypes = planeTypeRepository.findAll();
        return planeTypes;
    }

    public PlaneType save(PlaneType planeType) {
        planeTypeRepository.save(planeType);
        return planeType;
    }

    public void delete(Long id) {
        planeTypeRepository.deleteById(id);
    }

    public void update(Long id, String name, String constructorName, int nbPlace, int enginePower) {
        PlaneType planeType = planeTypeRepository.findById(id).orElseThrow();
        planeType.setName(name);
        planeType.setConstructorName(constructorName);
        planeType.setEnginePower(enginePower);
        planeType.setNbPlaces(nbPlace);
        planeTypeRepository.save(planeType);
    }
}
