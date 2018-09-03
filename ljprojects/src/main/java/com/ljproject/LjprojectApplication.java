package com.ljproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
@EntityScan(basePackageClasses = {
		LjprojectApplication.class,
		Jsr310JpaConverters.class
})
public class LjprojectApplication {
	public static final Logger logger = LoggerFactory.getLogger(LjprojectApplication.class);
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {

		SpringApplication.run(LjprojectApplication.class, args);

	}

}
