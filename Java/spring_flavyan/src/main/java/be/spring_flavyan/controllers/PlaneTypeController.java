package be.spring_flavyan.controllers;

import be.spring_flavyan.entities.PlaneType;
import be.spring_flavyan.repositories.PlaneTypeRepository;
import be.spring_flavyan.services.PlaneTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/planeType")
@RequiredArgsConstructor
public class PlaneTypeController {

    private final PlaneTypeService planeTypeService;

    @GetMapping
    public String getAllPlaneTypes(Model model) {
        model.addAttribute("planeTypes", planeTypeService.findAll());
        return "plane";
    }

    @PostMapping
    public String savePlaneType(Model model, PlaneType planeType) {
        planeTypeService.save(planeType);
        return "redirect:/plane";
    }

    @PostMapping("/{id}/delete")
    public String deletePlaneTypesById(@PathVariable Long id) {
        planeTypeService.delete(id);
        return "redirect:/plane";
    }

    @PostMapping("/{id}")
    public String updatePlaneTypeById(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String constructorName,
            @RequestParam Integer enginePower,
            @RequestParam Integer nbPlace
    ){
        planeTypeService.update(id, name, constructorName, enginePower, nbPlace);
        return "redirect:/plane";
    }
}
