package com.tiy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {MustacheAutoConfiguration.class})
public class GameTrackerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameTrackerSpringApplication.class, args);
	}
}
