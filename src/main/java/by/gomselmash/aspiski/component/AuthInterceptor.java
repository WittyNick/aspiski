package by.gomselmash.aspiski.component;

import by.gomselmash.aspiski.service.LogInService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final LogInService service;

    public AuthInterceptor(LogInService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (service.isAuthorizationDisabled()) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("userRole".equals(cookie.getName()) && "ROLE_ADMIN".equals(cookie.getValue())) {
                return true;
            }
        }
        return false;
    }
}
