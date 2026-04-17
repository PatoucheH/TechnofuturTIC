package be.spring_flavyan.services;

import be.spring_flavyan.entities.Plane;
import be.spring_flavyan.repositories.PlaneRepository;
import be.spring_flavyan.repositories.PlaneTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;
    private final PlaneTypeRepository planeTypeRepository;

    public List<Plane> findAll() {
        List<Plane> planes = planeRepository.findAll();
        return planes;
    }

    public Plane save(Plane plane) {
        planeRepository.save(plane);
        return plane;
    }

    public void delete(Long id) {
        planeRepository.deleteById(id);
    }

    public void update(Long id, String imma, Long planeTypeId) {
        Plane plane = planeRepository.findById(id).orElseThrow();
        plane.setImma(imma);
        plane.setPlaneType(planeTypeRepository.findById(planeTypeId).orElseThrow());
        planeRepository.save(plane);
    }
}
