package be.spring_flavyan.controllers;

import be.spring_flavyan.entities.fiscal.Fiscal;
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
@RequestMapping("/pilot")
@RequiredArgsConstructor
public class PilotController {
    private final PilotService pilotService;
    private final MechanicService mechanicService;
    private final FiscalService fiscalService;

    @GetMapping
    public String getAllPilots(Model model) {
        model.addAttribute("fiscals", fiscalService.findAll());
        model.addAttribute("pilots", pilotService.findAll());
        model.addAttribute("mechanics", mechanicService.findAll());
        model.addAttribute("pilot", new Pilot());
        model.addAttribute("fiscal", new Fiscal());
        return "fiscals";
    }

    @PostMapping
    public String addPilot(@ModelAttribute @Valid Pilot pilot, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fiscals", fiscalService.findAllOnlyFiscal());
            model.addAttribute("pilots", pilotService.findAll());
            model.addAttribute("mechanics", mechanicService.findAll());
            model.addAttribute("pilot", pilot);
            model.addAttribute("fiscal", new Fiscal());
            return "fiscals";
        }
        pilotService.save(pilot);
        return "redirect:/fiscal";
    }

    @PostMapping("/{id}/delete")
    public String deletePilot(@PathVariable Long id) {
        pilotService.delete(id);
        return "redirect:/fiscal";
    }

    @PostMapping("/{id}")
    public String updatePilot(@PathVariable Long id, @RequestParam String name, @RequestParam String brevetNumber) {
        pilotService.update(id, name, brevetNumber);
        return "redirect:/fiscal";
    }
}
