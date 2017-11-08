package com.jane.das;

import com.jane.das.commons.config.repository.impl.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class DasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DasApplication.class, args);
	}
}
