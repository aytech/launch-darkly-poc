package com.demo.launchdarkly.configuration;

import com.launchdarkly.logging.Logs;
import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.server.Components;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;

public final class LaunchDarklyClient {

    private static LDClient ldClient;
    private static LDUser ldUser;

    public static LDClient getLdClient() {
        return ldClient;
    }

    public static LDUser getLdUser() {
        return ldUser;
    }

    public static void initializeLdClient(String sdkKey) {
        LDConfig config = new LDConfig.Builder().logging(Components.logging(Logs.basic())).build();
        ldClient = new LDClient(sdkKey, config);
    }

    public static void initializeUser() {
        ldUser = new LDUser.Builder("some-random-string").name("Oleg").build();
    }
}
