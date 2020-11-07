package by.gomselmash.aspiski.component;

import org.apache.catalina.Context;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.stereotype.Component;

/**
 * This is to prevent cookie SameSite state warning in browser.
 */
@Component
public class SameSiteCookieTomcatContextCustomizer implements TomcatContextCustomizer {

    @Override
    public void customize(final Context context) {
        Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
        cookieProcessor.setSameSiteCookies("Lax");
        context.setCookieProcessor(cookieProcessor);
    }
}
