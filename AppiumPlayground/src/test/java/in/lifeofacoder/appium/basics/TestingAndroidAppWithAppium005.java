package in.lifeofacoder.appium.basics;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestingAndroidAppWithAppium005 {

    @Test(enabled = false)
    public void locatorStrategiesTest1() throws MalformedURLException {

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

        /*
            1. Open the 'ApiDemos' app
            2. Click on 'Preferences'
            3. Click on '3. Preference dependencies'
            4. Check the checkbox for 'WiFi'
            5. Click on 'WiFi Settings'
            6. Enter 'Hello' inside the popup
            7. Click on the 'OK' button
         */
        /*
            Most preferable locator strategies:

            1. Accessibility ID:
                - Android -> content-desc
                - iOS -> accessibility-id
                - Single element -> AndroidElement / MobileElement / DefaultGenericMobileElement / RemoteWebElement / WebElement (Declared generic type)
                - Multiple elements -> List<AndroidElement> / List<MobileElement> / List<DefaultGenericMobileElement> / List<RemoteWebElement> / List<WebElement> (Declared generic type)
            2. Class Name:
                - Single element -> AndroidElement / MobileElement / DefaultGenericMobileElement / RemoteWebElement / WebElement (Declared generic type)
                - Multiple elements -> List<WebElement>
            3. ID:
                - Android -> resource-id
                - iOS -> name
                - Single element -> AndroidElement / MobileElement / DefaultGenericMobileElement / RemoteWebElement / WebElement (Declared generic type)
                - Multiple elements -> List<WebElement>
            4. XPath
                - Single element -> AndroidElement / MobileElement / DefaultGenericMobileElement / RemoteWebElement / WebElement (Declared generic type)
                - Multiple elements -> List<WebElement>
            5. AndroidUiAutomator
                - Single element -> AndroidElement / MobileElement / DefaultGenericMobileElement / RemoteWebElement / WebElement (Declared generic type)
                - Multiple elements -> List<AndroidElement> / List<MobileElement> / List<DefaultGenericMobileElement> / List<RemoteWebElement> / List<WebElement> (Declared generic type)

            There are many more locator strategies, but we don't have much use cases for those
         */
        driver.findElementByAccessibilityId("Preference").click();
        driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
        driver.findElementById("android:id/checkbox").click();
        driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
        driver.findElementByClassName("android.widget.EditText").sendKeys("Hello");
        driver.findElementsByClassName("android.widget.Button").get(1).click();
    }
}
