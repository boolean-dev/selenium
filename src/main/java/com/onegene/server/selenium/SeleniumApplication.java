package com.onegene.server.selenium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SeleniumApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(SeleniumApplication.class, args);

//		ApplicationContext context = SpringApplication.run(SeleniumApplication.class, args);
//		ConfigurableApplicationContext context = new SpringApplicationBuilder(SeleniumApplication.class).headless(false).run(args);
//		AppFrame appFrame = context.getBean(AppFrame.class);
	}

}

