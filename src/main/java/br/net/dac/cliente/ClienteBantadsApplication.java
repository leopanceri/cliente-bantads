package br.net.dac.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class ClienteBantadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteBantadsApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
