package be.spring_flavyan.controllers;

import be.spring_flavyan.entities.fiscal.Fiscal;
import be.spring_flavyan.entities.fiscal.Mechanic;
import be.spring_flavyan.entities.fiscal.Pilot;
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
        model.addAttribute("pilot", new Pilot());
        model.addAttribute("fiscal", new Fiscal());
        return "fiscals";
    }

    @PostMapping
    public String addMechanic(@ModelAttribute @Valid Mechanic mechanic, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fiscals", fiscalService.findAllOnlyFiscal());
            model.addAttribute("pilots", pilotService.findAll());
            model.addAttribute("mechanics", mechanicService.findAll());
            model.addAttribute("pilot", new Pilot());
            model.addAttribute("fiscal", new Fiscal());
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
    public String updateMechanic(@PathVariable Long id, @RequestParam String name) {
        mechanicService.update(id, name);
        return "redirect:/fiscal";
    }
}
