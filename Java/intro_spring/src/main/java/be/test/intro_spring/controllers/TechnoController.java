package be.test.intro_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tf")
public class TechnoController {

    @GetMapping("/java/thebest")
    public String weAreTheBest() {
        return "java/thebest";
    }
}
