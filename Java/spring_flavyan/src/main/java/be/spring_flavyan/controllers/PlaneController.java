package be.spring_flavyan.controllers;

import be.spring_flavyan.entities.Plane;
import be.spring_flavyan.entities.PlaneType;
import be.spring_flavyan.repositories.PlaneRepository;
import be.spring_flavyan.repositories.PlaneTypeRepository;
import be.spring_flavyan.services.PlaneService;
import be.spring_flavyan.services.PlaneTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plane")
@RequiredArgsConstructor
public class PlaneController {
    private final PlaneService planeService;
    private final PlaneTypeService planeTypeService;

    @GetMapping
    public String getAllPlanes(Model model) {
        model.addAttribute("planes", planeService.findAll());
        model.addAttribute("planeTypes", planeTypeService.findAll());
        return "plane";
    }

    @PostMapping
    public String savePlane(Plane plane) {
        planeService.save(plane);
        return "redirect:/plane";
    }

    @PostMapping("/{id}/delete")
    public String deletePlane(@PathVariable Long id) {
        planeService.delete(id);
        return "redirect:/plane";
    }

    @PostMapping("/{id}")
    public String updatePlane(@PathVariable Long id, @RequestParam String imma, @RequestParam Long planeTypeId) {
        planeService.update(id, imma, planeTypeId);
        return "redirect:/plane";
    }
}
