package com.app.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
//		Dotenv dotenv=Dotenv.load();
//		for (DotenvEntry entry: dotenv.entries()){
//			System.setProperty(entry.getKey(),entry.getValue());
//		}
		SpringApplication.run(ServerApplication.class, args);
	}

}
