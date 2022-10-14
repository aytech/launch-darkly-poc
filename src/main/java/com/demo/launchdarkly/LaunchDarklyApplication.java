package com.demo.launchdarkly;

import com.demo.launchdarkly.configuration.LaunchDarklyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import static com.demo.launchdarkly.configuration.Global.LD_CLIENT_ID;

@SpringBootApplication
public class LaunchDarklyApplication {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(LaunchDarklyApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			LaunchDarklyClient.initializeLdClient(environment.getProperty(LD_CLIENT_ID));
			LaunchDarklyClient.initializeUser();
		};
	}
}
