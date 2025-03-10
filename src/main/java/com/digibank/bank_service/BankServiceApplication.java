package com.digibank.bank_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BankServiceApplication {

	static {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
	}

	public static void main(String[] args) {
		Environment env = SpringApplication.run(BankServiceApplication.class, args).getEnvironment();
		System.out.println("Currently running on PORT: " + env.getProperty("local.server.port", "8082"));
		SpringApplication.run(BankServiceApplication.class, args);
	}

}
