package in.lifeofacoder.appium.basics;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestingAndroidAppWithAppium006 {

    @Test(enabled = false)
    public void locatorStrategiesTest2() throws MalformedURLException {

        // Opening app with all required capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4a");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);

        // Syntax for finding an element by its attribute with AndroidUiAutomator: attribute("value")
        // Syntax for finding an element by its property with AndroidUiAutomator: new UiSelector().property("value")
        // Note: In both the above cases we can't use single quotes(') to surround the value (which is different from writing an XPath or a CssSelector in Selenium)

        // Click on the 'Views' button
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

        // Find number of available buttons on the current page
        int noOfAvailableButtons = driver.findElementsById("android:id/text1").size();
        System.out.println(noOfAvailableButtons);

        // Find number of clickable buttons on the current page
        int noOfClickableButtons = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size();
        System.out.println(noOfClickableButtons);

        // Validate whether all the buttons available on the page are clickable or not
        Assertions.assertThat(noOfAvailableButtons)
                .as("'No of available buttons' on the page, do not match with 'No of clickable buttons' on the page")
                .isEqualTo(noOfClickableButtons);
    }
}
