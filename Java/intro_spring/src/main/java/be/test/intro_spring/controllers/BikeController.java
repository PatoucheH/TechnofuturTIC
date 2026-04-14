package be.test.intro_spring.controllers;

import be.test.intro_spring.entities.Bike;
import be.test.intro_spring.repositories.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bike")
@RequiredArgsConstructor
public class BikeController {

    private final BikeRepository bikeRepository;

    @GetMapping
    public String getAllBikes(
            @RequestParam(value = "searchBrand", required = false) String search
            , Model model
    ) {
        if(search != null && !search.trim().isEmpty()){
            model.addAttribute("bikes", bikeRepository.findAll().stream()
                    .filter(b -> b.getBrand().toLowerCase().contains(search.toLowerCase()))
                    .toList()
            );
        }else{
            model.addAttribute("bikes", bikeRepository.findAll());
        }
        return "/bike/allBikes";
    }

    @GetMapping("/{id}")
    public String getBike(@PathVariable int id, Model model) {
        model.addAttribute("bike", bikeRepository.findById(id).orElseThrow());
        return "/bike/details";
    }

    @PostMapping
    public String addBike(@ModelAttribute Bike bike) {
        bikeRepository.save(bike);
        return "redirect:/bike";
    }

}
