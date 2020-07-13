package com.sakti.debtoronboarding;

import java.net.UnknownHostException;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sakti.infrastructure", "com.sakti.debtoronboarding"})
public class SaktiDebtorOnboardingApplication {

	public void init(){
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SaktiDebtorOnboardingApplication.class, args);
	}

}
