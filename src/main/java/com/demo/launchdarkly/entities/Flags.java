package com.demo.launchdarkly.entities;

public class Flags {

    private boolean reactDemoFlag;
    private boolean springDemoFlag;

    public boolean isReactDemoFlag() {
        return reactDemoFlag;
    }

    public void setReactDemoFlag(boolean reactDemoFlag) {
        this.reactDemoFlag = reactDemoFlag;
    }

    public boolean isSpringDemoFlag() {
        return springDemoFlag;
    }

    public void setSpringDemoFlag(boolean springDemoFlag) {
        this.springDemoFlag = springDemoFlag;
    }
}
