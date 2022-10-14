package com.demo.launchdarkly.rest;

import com.demo.launchdarkly.configuration.LaunchDarklyClient;
import com.demo.launchdarkly.entities.Flags;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.demo.launchdarkly.configuration.FeatureToggles.DEMO_TOGGLE;

@RestController
public class LaunchDarklyController {

    @GetMapping("/flags")
    Flags getFlags() {
        Flags flags = new Flags();
        flags.setDemoFeatureToggle(
                LaunchDarklyClient
                        .getLdClient()
                        .boolVariation(DEMO_TOGGLE, LaunchDarklyClient.getLdUser(), false));
        return flags;
    }
}
