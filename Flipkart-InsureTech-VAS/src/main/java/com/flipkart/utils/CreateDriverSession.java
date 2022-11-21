package com.flipkart.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class CreateDriverSession {

    public static AppiumDriver getDriverInstance(String ip, int port, String platformName) throws MalformedURLException {

        UiAutomator2Options options = null;
        URL url = new URL("http://" + ip + ":" + port);

        switch (platformName) {

            case "Android_VirtualDevice":
                return new AndroidDriver(url, options);
            case "Android_RealDevice":
                options = new UiAutomator2Options()
                        .setPlatformName("Android")
                        .setPlatformVersion("12.0")
                        .setDeviceName("Redmi Note 10 Pro")
                        .setAutomationName("UiAutomator2")
                        .setUdid("78aab321")
                        .setAppPackage("com.flipkart.android")
                        .setAppActivity("com.flipkart.android.activity.HomeFragmentHolderActivity")
                        .setAutoGrantPermissions(true)
                        .setNoReset(true);

                return new AndroidDriver(url, options);
            case "iOS_VirtualDevice":
                return new IOSDriver(url, options);
            case "iOS_RealDevice":
                return new IOSDriver(url, options);
            default:
                throw new IllegalArgumentException("Invalid platform name given");
        }
    }
}
