package com.emilianosusmano.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
//		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		SpringApplication.run(App.class, args);
	}
}
