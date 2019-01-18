package com.onegene.server.selenium;

import com.onegene.server.selenium.gui.AppFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SeleniumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeleniumApplication.class, args);

//		ApplicationContext context = SpringApplication.run(SeleniumApplication.class, args);
//		ConfigurableApplicationContext context = new SpringApplicationBuilder(SeleniumApplication.class).headless(false).run(args);
//		AppFrame appFrame = context.getBean(AppFrame.class);
	}

}

