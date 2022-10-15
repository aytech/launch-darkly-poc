package com.demo.launchdarkly.rest;

import com.demo.launchdarkly.configuration.LaunchDarklyClient;
import com.demo.launchdarkly.entities.Flags;
import com.demo.launchdarkly.entities.RestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.demo.launchdarkly.configuration.Global.*;

@RestController
public class LaunchDarklyController {

    @Autowired
    private Environment environment;

    @Autowired
    private LaunchDarklyClient ldClient;

    @GetMapping("/flags")
    public Flags getFlags() {
        Flags flags = new Flags();
        flags.setReactDemoFlag(ldClient.getCurrentValue(REACT_DEMO_FLAG));
        flags.setSpringDemoFlag(ldClient.getCurrentValue(SPRING_DEMO_FLAG));
        return flags;
    }

    @GetMapping("/configuration")
    RestConfiguration getConfiguration() {
        RestConfiguration configuration = new RestConfiguration();
        configuration.setLdClientId(environment.getProperty(LD_CLIENT_ID));
        return configuration;
    }
}
