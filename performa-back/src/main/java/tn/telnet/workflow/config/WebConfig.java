package tn.telnet.workflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/engine-rest/**")
                .allowedOrigins("http://localhost:4200") // Update with your allowed origin
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("X-Requested-With", "Content-Type", "X-Codingpedia")
                .exposedHeaders("Access-Control-Allow-Origin")
                .allowCredentials(false).maxAge(3600);
    }
}