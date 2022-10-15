package com.demo.launchdarkly.configuration;

import java.io.FileNotFoundException;

public interface ILaunchDarklyClient {
    void initializeOnlineClient(String sdkKey);
    void initializeOfflineClient(String dataLocation) throws FileNotFoundException;
    void registerChangeListener(String flagKey);
}
