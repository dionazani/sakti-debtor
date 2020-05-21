package com.sakti.debtoronboarding.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = {"com.sakti.dao", "com.sakti.debtoronboarding"})
public class SaktiDebtorOnboardingApplicationTests {

	@Test
	public void contextLoads() {
	}

}
