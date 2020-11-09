package by.gomselmash.aspiski.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogInService {

    @Value("${user.password:}")
    private String password;

    public boolean isPasswordValid(String formPassword) {
        return isAuthorizationDisabled() || password.equals(formPassword);
    }

    public boolean isAuthorizationDisabled() {
        return password.isEmpty();
    }
}
