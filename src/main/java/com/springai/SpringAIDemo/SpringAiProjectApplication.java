package com.springai.SpringAIDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="file:C:/Users/JanH/OneDrive/Documents/ApplicationProperties/application.properties")
public class SpringAiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiProjectApplication.class, args);
	}

}
