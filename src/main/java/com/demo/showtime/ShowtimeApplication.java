package com.demo.showtime;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication(scanBasePackages = "com.demo.showtime")
@EnableJpaRepositories
@Controller
public class ShowtimeApplication extends WebMvcConfigurerAdapter {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/tasks/new").setViewName("addtask");
        registry.addViewController("/tasks").setViewName("tasks");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/test").setViewName("x");
    }
    public static void main(String[] args) {
        SpringApplication.run(ShowtimeApplication.class, args);


    }

}
