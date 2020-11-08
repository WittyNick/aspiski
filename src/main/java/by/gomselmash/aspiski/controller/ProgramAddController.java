package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.Program;
import by.gomselmash.aspiski.service.ProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class ProgramAddController {
    private final ProgramService service;

    public ProgramAddController(ProgramService service) {
        this.service = service;
    }

    @GetMapping("/programAdd")
    public String goProgramAdd(@CookieValue(value = "userId", defaultValue = "0") String userId, Model model) {
        Integer id = Integer.valueOf(userId);
        Map<String, Object> dropdownMap = service.getEntityMap();
        model
                .addAllAttributes(dropdownMap)
                .addAttribute("userId", id);
        return "program_add";
    }

    @PostMapping("/saveProgram")
    @ResponseBody
    public Boolean saveProgram(@RequestBody Program program, HttpServletResponse response) {
        String userId = String.valueOf(program.getDeveloper().getId());
        Cookie cookie = new Cookie("userId", userId);
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return service.saveProgram(program);
    }
}
