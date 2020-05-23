package com.sakti.debtoronboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sakti.infrastructure", "com.sakti.debtoronboarding"})
public class SaktiDebtorOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaktiDebtorOnboardingApplication.class, args);
	}

}
