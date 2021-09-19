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

public class TestingAndroidAppWithAppium008 {

    @Test(enabled = true)
    public void swipeTest() throws MalformedURLException {

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
            1. Click on 'Views'
            2. Click on 'Date Widgets'
            3. Click on '2. Inline'
            4. Click on '12'
            5. Press and hold on '15' and then swipe left to '45'
         */
        driver.findElementByAccessibilityId("Views").click();
        driver.findElementByAccessibilityId("Date Widgets").click();
        driver.findElementByAccessibilityId("2. Inline").click();

        /*
            Note: If there is any special character in the class name (i.e. The '$' character in 'android.widget.RadialTimePickerView$RadialPickerTouchHelper')
            then while writing XPath replace the special character with dot (.) so that Appium can recognise it.
         */
//        driver.findElementByXPath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc='12']").click();
        driver.findElementByXPath("//*[@content-desc='12']").click();

        AndroidTouchAction touch = new AndroidTouchAction(driver);
        AndroidElement min15 = driver.findElementByAccessibilityId("15");
        AndroidElement min45 = driver.findElementByAccessibilityId("45");
        touch.longPress(LongPressOptions
                        .longPressOptions()
                        .withElement(ElementOption.element(min15))
                        .withDuration(Duration.ofSeconds(1)))
                        .moveTo(ElementOption.element(min45))
                        .release()
                        .perform();
    }
}
