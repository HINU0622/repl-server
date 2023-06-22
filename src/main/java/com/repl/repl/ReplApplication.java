package com.repl.repl;

import com.repl.repl.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({JwtProperties.class})
@SpringBootApplication
public class ReplApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplApplication.class, args);
	}

}
