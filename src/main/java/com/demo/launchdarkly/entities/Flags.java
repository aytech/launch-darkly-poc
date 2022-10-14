package com.demo.launchdarkly.entities;

public class Flags {

    private boolean demoFeatureToggle;

    public boolean isDemoFeatureToggle() {
        return demoFeatureToggle;
    }

    public void setDemoFeatureToggle(boolean demoFeatureToggle) {
        this.demoFeatureToggle = demoFeatureToggle;
    }
}
