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
@RequestMapping("/fiscal")
@RequiredArgsConstructor
public class FiscalController {

    private final FiscalService fiscalService;
    private final PilotService pilotService;
    private final MechanicService mechanicService;

    @GetMapping
    public String getAllFiscals(Model model) {
        model.addAttribute("fiscals", fiscalService.findAllOnlyFiscal());
        model.addAttribute("pilots", pilotService.findAll());
        model.addAttribute("mechanics", mechanicService.findAll());
        model.addAttribute("fiscal", new FiscalRequest());
        model.addAttribute("pilot", new PilotRequest());
        model.addAttribute("mechanic", new MechanicRequest());
        return "fiscals";
    }

    @PostMapping
    public String addFiscal(@ModelAttribute("fiscal") @Valid FiscalRequest fiscal, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fiscals", fiscalService.findAllOnlyFiscal());
            model.addAttribute("pilots", pilotService.findAll());
            model.addAttribute("mechanics", mechanicService.findAll());
            model.addAttribute("fiscal", fiscal);
            model.addAttribute("pilot", new PilotRequest());
            model.addAttribute("mechanic", new MechanicRequest());
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
    public String updateFiscal(@PathVariable Long id,
            @RequestParam String name,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String zip,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String state) {
        fiscalService.update(id, name, street, city, zip, country, state);
        return "redirect:/fiscal";
    }
}
