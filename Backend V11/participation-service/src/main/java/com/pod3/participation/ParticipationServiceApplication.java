package com.pod3.participation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableFeignClients
@Slf4j
@EnableWebMvc
public class ParticipationServiceApplication {

	public static void main(String[] args) {
		log.debug("START");
		SpringApplication.run(ParticipationServiceApplication.class, args);
		log.debug("END");
	}

}
