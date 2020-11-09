package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.model.*;
import by.gomselmash.aspiski.service.WelcomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WelcomeController {
    private final WelcomeService service;

    public WelcomeController(WelcomeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String goFindByPart(Model model, HttpServletRequest request) {
        List<Program> programs = service.findAllPrograms();
        List<Machine> machines = service.findAllMachines();
        List<ControlSystem> controlSystems = service.findAllControlSystems();
        List<Workshop> workshops = service.findAllWorkshops();
        List<Developer> developers = service.findAllDevelopers();
        String authority = getAuthority(request);
        model
                .addAttribute("programs", programs)
                .addAttribute("machines", machines)
                .addAttribute("controlSystems", controlSystems)
                .addAttribute("workshops", workshops)
                .addAttribute("developers", developers)
                .addAttribute("authority", authority);
        return "welcome";
    }

    @PostMapping("/deleteProgram")
    @ResponseBody
    public Boolean deleteProgram(@RequestBody String programId) {
        Long id = Long.valueOf(programId);
        return service.deleteProgramById(id);
    }

    private String getAuthority(HttpServletRequest request) {
        String authority = "ROLE_USER";
        if (service.isUserAuthorizationDisabled()) {
            authority = "DISABLED";
        } else if (isUserAdmin(request)) {
            authority = "ROLE_ADMIN";
        }
        return authority;
    }

    private boolean isUserAdmin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("userRole".equals(cookie.getName()) && "ROLE_ADMIN".equals(cookie.getValue())) {
                return true;
            }
        }
        return false;
    }
}
