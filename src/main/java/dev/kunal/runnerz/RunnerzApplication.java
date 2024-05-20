package dev.kunal.runnerz;

import dev.kunal.runnerz.run.Location;
import dev.kunal.runnerz.run.Run;
import dev.kunal.runnerz.run.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class RunnerzApplication {

	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);

		var welcomeMessage = new WelcomeMessage();
		System.out.println(welcomeMessage.getWelcomeMessage());

		log.info("Runnerz Application is started successfully...");

	}
	@Bean
	CommandLineRunner runner(RunRepository RunRepository) {
		return args -> {
			Run run = new Run(1, "First Run", LocalDateTime.now(),
					LocalDateTime.now().plus(1, ChronoUnit.HOURS),
					5, Location.OUTDOOR);
			RunRepository.create(run);
			log.info("Run : " + run);
		};
	}

}
