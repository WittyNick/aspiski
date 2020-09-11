package by.gomselmash.aspiski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindByWorkshopController {

    @GetMapping("/find-by-workshop")
    public String goFindByWorkshop() {
        return "find_by_workshop";
    }
}
