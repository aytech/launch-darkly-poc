package com.demo.launchdarkly.configuration;

import com.launchdarkly.logging.Logs;
import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.server.Components;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import com.launchdarkly.sdk.server.integrations.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

@Component("ldClient")
public class LaunchDarklyClient implements ILaunchDarklyClient {

    @Autowired
    ResourceLoader resourceLoader;

    private LDClient ldClient;
    private LDUser ldUser;

    public void initializeOnlineClient(String sdkKey) {
        LDConfig config = new LDConfig.Builder().logging(Components.logging(Logs.basic())).build();
        ldClient = new LDClient(sdkKey, config);
    }

    @Override
    public void initializeOfflineClient(String fileName) throws FileNotFoundException {
        Path resourcePath;
        try {
            resourcePath = resourceLoader.getResource(String.format("classpath:%s", fileName)).getFile().toPath();
        } catch (IOException ex) {
            throw new FileNotFoundException(String.format("File %s not found", fileName));
        }

        ldClient = new LDClient(
                "some-dummy-key",
                new LDConfig.Builder().dataSource(
                                FileData
                                        .dataSource()
                                        .filePaths(resourcePath)
                                        .autoUpdate(true))
                        .events(Components.noEvents()).build());
        initiateLDUser();
    }

    @Override
    public void registerChangeListener(String flagKey) {
        ldClient
                .getFlagTracker()
                .addFlagValueChangeListener(flagKey, ldUser, System.out::println);
    }

    private void initiateLDUser() {
        ldUser = new LDUser.Builder("some-random-string").name("Oleg").build();
    }

    public Boolean getCurrentValue(String flagKey) {
        return ldClient.boolVariation(flagKey, ldUser, false);
    }
}
