package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddWorkshopController {

    @GetMapping("/add-workshop")
    public String goAddWorkshop() {
        return "add_workshop";
    }
}
