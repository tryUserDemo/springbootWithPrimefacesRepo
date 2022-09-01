package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.demo.projectConfiguration.JsfPrimefacesConfig;

@SpringBootApplication
@Configuration
@Import(JsfPrimefacesConfig.class)
@ComponentScan("com.example.demo")
public class SpringWithPrimefacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithPrimefacesApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
