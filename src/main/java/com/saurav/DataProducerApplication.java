package com.saurav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class DataProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProducerApplication.class, args);
	}
}

interface OutBoundChannel {
	String OUTBOUND_DATE = "irtlsdata";

	@Output(OUTBOUND_DATE)
	MessageChannel outboundChannel();
}

@Configuration
class Config {

	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}