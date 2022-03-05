package com.zzangho.moviebatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class MovieBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBatchApplication.class, args);
	}

}
