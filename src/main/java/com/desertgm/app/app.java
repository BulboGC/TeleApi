package com.desertgm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class app {

	public static void main(String[] args) {
		SpringApplication.run(app.class, args);

	}

}
