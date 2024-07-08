package br.com.diogenes.maxclubcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MaxclubcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaxclubcardApplication.class, args);
	}

}
