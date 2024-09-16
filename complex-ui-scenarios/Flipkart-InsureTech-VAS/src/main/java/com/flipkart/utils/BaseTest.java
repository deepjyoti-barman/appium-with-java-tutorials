package com.flipkart.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;

public class BaseTest {
    AppiumDriverLocalService service = null;
    protected AppiumDriver driver = null;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        String ip = "127.0.0.1";
        int port = 4723;

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress(ip)
                .usingPort(port)
                .build();
        service.start();

        driver = CreateDriverSession.getDriverInstance(ip, port,"Android_RealDevice");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterTest
    public void tearDown() {

        if (driver != null)
            driver.quit();

        if (service != null)
            service.stop();
    }
}
