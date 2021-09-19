package in.lifeofacoder.appium.basics;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestingAndroidAppWithAppium003 {

    @Test(enabled = false)
    public void installAndOpenApp() throws MalformedURLException {

        // Installing and opening app with all required capabilities (old approach)
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", new File("src/main/resources/android-apps/ApiDemos-debug.apk").getAbsolutePath());
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "Pixel 4a");
        capabilities.setCapability("platformName", "Android");      // Used for distinguishing the platform (Android / iOS / Microsoft)
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("udid", "emulator-5554");        // Used for distinguishing the devices, when multiple devices are running on same platform

//        AppiumDriver<MobileElement> driver = new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        AppiumDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }
}
