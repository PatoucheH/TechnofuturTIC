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
@RequestMapping("/fiscal")
@RequiredArgsConstructor
public class FiscalController {

    private final FiscalService fiscalService;
    private final PilotService pilotService;
    private final MechanicService mechanicService;

    @GetMapping
    public String getAllFiscals(Model model){
        model.addAttribute("fiscals",fiscalService.findAllOnlyFiscal());
        model.addAttribute("pilots",pilotService.findAll());
        model.addAttribute("mechanics",mechanicService.findAll());
        model.addAttribute("fiscal",  new Fiscal());
        model.addAttribute("pilot", new Pilot());
        return "fiscals";
    }

    @PostMapping
    public String addFiscal(@ModelAttribute @Valid Fiscal fiscal, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("fiscals",  fiscalService.findAllOnlyFiscal());
            model.addAttribute("pilots", pilotService.findAll());
            model.addAttribute("mechanics", mechanicService.findAll());
            model.addAttribute("fiscal",  new Fiscal());
            return "fiscals";
        }
        fiscalService.save(fiscal);
        return "redirect:/fiscal";
    }

    @PostMapping("/{id}/delete")
    public String deleteFiscal(@PathVariable Long id) {
        fiscalService.delete(id);
        return "redirect:/fiscal";
    }

    @PostMapping("/{id}")
    public String updateFiscal(@PathVariable Long id, @RequestParam String name) {
        fiscalService.update(id, name);
        return "redirect:/fiscal";
    }
}
