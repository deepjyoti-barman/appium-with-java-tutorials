package in.lifeofacoder.appium.gestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestingAndroidAppWithAppium007 {

    @Test(enabled = false)
    public void tapTest() throws MalformedURLException {

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
            2. Click on 'Views'
            3. Click on 'Expandable Lists'
            4. Click on '1. Custom Adapter'
            5. Press and hold 'People Names' for 2 seconds
            6. Validate whether 'Sample Menu' popup has appeared or not
         */
        /*
            tap():
                - Tap means touch your finger on an element.
                - It is more closely bound to the mobile scenario.
                - It is a part of AppiumDriver.
            click():
                - Click means deliberately press or hold on an element.
                - It is more closely bound to the web scenario
                - It is a part of WebDriver.

            Note: The end result of tap() and click() are the same, but for mobile testing use cases using tap() is more preferable.
            Note: TouchAction class belongs to Appium and is made for handling gestures in mobile apps, while TouchActions class belongs to Selenium.
        */
        driver.findElementByAccessibilityId("Views").click();

        // Process 1
        // <?> is used to indicate some unknown type. It can accept any type.
        // Official GitHub Link on TouchAction: https://github.com/appium/java-client/blob/master/src/test/java/io/appium/java_client/android/AndroidElementTest.java
        TouchAction<?> action = new TouchAction<>(driver);
        AndroidElement expandableListsButton = driver.findElementByAccessibilityId("Expandable Lists");
        action.tap(TapOptions
                        .tapOptions()
                        .withElement(ElementOption.element(expandableListsButton)))
                        .perform();

        // Process 2
        AndroidTouchAction touch = new AndroidTouchAction(driver);
        AndroidElement customAdapterButton = driver.findElementByAccessibilityId("1. Custom Adapter");
        touch.tap(TapOptions
                        .tapOptions()
                        .withElement(ElementOption.element(customAdapterButton)))
                        .perform();

        AndroidElement peopleNamesButton = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
        touch.longPress(LongPressOptions
                        .longPressOptions()
                        .withElement(ElementOption.element(peopleNamesButton))
                        .withDuration(Duration.ofSeconds(2)))
                        .release()
                        .perform();

        boolean isPopupVisible = driver.findElementByAndroidUIAutomator("text(\"Sample action\")").isDisplayed();
        Assertions.assertThat(isPopupVisible)
                .as("'Sample action button is not visible")
                .isTrue();
    }
}
