package io.proj3ct.WaxSpringBot;



import io.proj3ct.WaxSpringBot.config.BotConfig;
import io.proj3ct.WaxSpringBot.service.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WaxSpringBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaxSpringBotApplication.class, args);
	}

}
