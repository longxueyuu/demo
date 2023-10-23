package com.example.demo;

import com.example.restservice.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.restservice")
public class DemoApplication {


	public static void main(String[] args) {
		System.out.println("main start ");

		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		System.out.println(context.getBean(Config.class));
	}

}
