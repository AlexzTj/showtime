package com.demo.showtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(scanBasePackages = "com.demo.showtime")
@EnableJpaRepositories
@Controller
public class ShowtimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowtimeApplication.class, args);

	}
	@RequestMapping("/")
	public String home(){
		return "home";
	}
}
