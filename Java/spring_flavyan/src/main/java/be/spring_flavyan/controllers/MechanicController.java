package be.spring_flavyan.controllers;

import be.spring_flavyan.dtos.request.FiscalRequest;
import be.spring_flavyan.dtos.request.MechanicRequest;
import be.spring_flavyan.dtos.request.PilotRequest;
import be.spring_flavyan.services.FiscalService;
import be.spring_flavyan.services.MechanicService;
import be.spring_flavyan.services.PilotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mechanic")
@RequiredArgsConstructor
public class MechanicController {

    private final MechanicService mechanicService;
    private final PilotService pilotService;
    private final FiscalService fiscalService;

    @GetMapping
    public String getAllMechanics(Model model) {
        model.addAttribute("fiscals", fiscalService.findAll());
        model.addAttribute("pilots", pilotService.findAll());
        model.addAttribute("mechanics", mechanicService.findAll());
        model.addAttribute("pilot", new PilotRequest());
        model.addAttribute("fiscal", new FiscalRequest());
        model.addAttribute("mechanic", new MechanicRequest());
        return "fiscals";
    }

    @PostMapping
    public String addMechanic(@ModelAttribute("mechanic") @Valid MechanicRequest mechanic, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fiscals", fiscalService.findAllOnlyFiscal());
            model.addAttribute("pilots", pilotService.findAll());
            model.addAttribute("mechanics", mechanicService.findAll());
            model.addAttribute("mechanic", mechanic);
            model.addAttribute("pilot", new PilotRequest());
            model.addAttribute("fiscal", new FiscalRequest());
            return "fiscals";
        }
        mechanicService.save(mechanic);
        return "redirect:/fiscal";
    }

    @PostMapping("/{id}/delete")
    public String deleteMechanic(@PathVariable Long id) {
        mechanicService.delete(id);
        return "redirect:/fiscal";
    }

    @PostMapping("/{id}")
    public String updateMechanic(@PathVariable Long id,
            @RequestParam String name,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String zip,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String state) {
        mechanicService.update(id, name, street, city, zip, country, state);
        return "redirect:/fiscal";
    }
}
