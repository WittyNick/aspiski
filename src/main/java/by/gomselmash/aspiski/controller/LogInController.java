package by.gomselmash.aspiski.controller;

import by.gomselmash.aspiski.service.LogInService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogInController {
    private final LogInService service;

    public LogInController(LogInService service) {
        this.service = service;
    }

    @GetMapping("/logIn")
    public String goLogIn(Model model) {
        model.addAttribute("hasError", Boolean.FALSE);
        return "log_in";
    }

    @PostMapping("/checkUser")
    @ResponseBody
    public Boolean checkUser(@RequestBody String formPassword, HttpServletResponse response) {
        if (service.isPasswordValid(formPassword)) {
            Cookie cookie = new Cookie("userRole", "ROLE_ADMIN");
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if ("userRole".equals(cookieName) || "userId".equals(cookieName)) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        request.getSession().invalidate();
        return "redirect:/";
    }
}
