package by.gomselmash.aspiski.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ServiceInterceptorAppConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    public ServiceInterceptorAppConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/*")
                .excludePathPatterns(
                        "/css/**", "/js/**", "/img/**",
                        "/", "/logIn/*"
                );
    }
}
