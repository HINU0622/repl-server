package com.repl.repl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class ReplApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplApplication.class, args);
	}

}
