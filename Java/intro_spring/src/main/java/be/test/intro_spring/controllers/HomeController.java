package be.test.intro_spring.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home() {
        return "home/home";
    }

    @GetMapping("/about")
    public String about(){
        return "home/about";
    }

//    @GetMapping("/hello/{name}")
//    public String hello(@PathVariable String name, Model model){
//        model.addAttribute("name", name);
//        return "home/hello";
//    }
    
    @GetMapping("/hello")
    public String hello(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        return "home/hello";
    }
}
