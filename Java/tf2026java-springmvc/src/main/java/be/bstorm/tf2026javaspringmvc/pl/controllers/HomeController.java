package be.bstorm.tf2026javaspringmvc.pl.controllers;

import be.bstorm.tf2026javaspringmvc.pl.models.home.PersonForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping({"/", ""})
    public String indexAction(Map<String, Object> model) {
        model.put("demo", "Home");
        model.put("message", "Welcome to the home page!");
        return "home/index";
    }

    @PostMapping("")
    public String postAction(
            Map<String, Object> model,
            @Valid @ModelAttribute PersonForm form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("flash_error", form);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("flash_success", "Form submitted successfully!");
        return "redirect:/";
    }
}
