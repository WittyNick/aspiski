package by.gomselmash.aspiski.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/logIn")
public class LogInController {

    @Value("${security.auth.enabled:false}") // :false - is default value
    private boolean isAuthenticationEnabled;

    @Value("${security.login:}") // :"" - (empty string) is default
    private String login;

    @Value("${security.pass:}")
    private String pass;

    @GetMapping
    public String goLogIn() {
        return "log_in";
    }

    @PostMapping("/checkUser")
    public String checkUser(@RequestParam(name = "user") String formUser,
                            @RequestParam(name = "password") String formPassword) {


        return "redirect:/";
    }


}
