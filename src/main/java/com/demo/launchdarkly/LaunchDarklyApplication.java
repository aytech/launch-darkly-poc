package com.demo.launchdarkly;

import com.demo.launchdarkly.configuration.LaunchDarklyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import static com.demo.launchdarkly.configuration.Global.LD_SDK_KEY;
import static com.demo.launchdarkly.configuration.Global.REACT_DEMO_FLAG;

@SpringBootApplication
public class LaunchDarklyApplication {

    @Value("${spring.profiles.active}")
    private String springProfileActive;

    @Autowired
    private Environment environment;

    @Autowired
    private LaunchDarklyClient ldClient;

    public static void main(String[] args) {
        SpringApplication.run(LaunchDarklyApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.printf("Profile: %s", springProfileActive);

            if (springProfileActive.equals("dev")) {
                ldClient.initializeOfflineClient("in-memory-flags.json");
            } else {
                ldClient.initializeOnlineClient(environment.getProperty(LD_SDK_KEY));
            }
            ldClient.registerChangeListener(REACT_DEMO_FLAG);
        };
    }
}
