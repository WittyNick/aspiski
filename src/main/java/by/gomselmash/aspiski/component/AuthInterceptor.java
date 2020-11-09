package by.gomselmash.aspiski.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("userRole".equals(cookie.getName()) && "ROLE_ADMIN".equals(cookie.getValue())) {
                return true;
            }
        }
        return false;
    }
}
