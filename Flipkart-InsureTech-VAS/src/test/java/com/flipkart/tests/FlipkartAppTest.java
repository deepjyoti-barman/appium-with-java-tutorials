package com.flipkart.tests;

import com.flipkart.utils.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class FlipkartAppTest extends BaseTest {

    @Test
    public void listAllInsurancesTest() {

        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Search for products']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Search for Products, Brands and More']")).sendKeys("realme 9 pro sunrise blue ");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='realme 9 pro sunrise blue']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='realme 9 Pro 5G (Sunrise Blue, 128 GB)']")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Protect Your Product\"));"));
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).setAsHorizontalList().scrollToEnd(4));"));

        int insuranceCouter = 1;
        boolean isTheLastInsuranceCardAvailable = false;

        while (true) {
            int totalInsuranceCardCounter = driver.findElements(By.xpath("(//android.widget.HorizontalScrollView)[1]/android.view.ViewGroup/android.view.ViewGroup")).size();

            // Handle lazy loading / dynamic loading-unloading of elements
            if (totalInsuranceCardCounter < insuranceCouter) {
                --insuranceCouter;
                isTheLastInsuranceCardAvailable = true;
            }

            String parentCardLocator = "(//android.widget.HorizontalScrollView)[1]/android.view.ViewGroup/android.view.ViewGroup[" + insuranceCouter++ + "]";
            String insuranceNameLocator = parentCardLocator + "/android.view.ViewGroup[1]/android.widget.TextView[1]";
            String insuranceTypeLocator = parentCardLocator + "/android.view.ViewGroup[1]/android.widget.TextView[2]";
            String currentPriceLocator = parentCardLocator + "//android.widget.TextView[starts-with(@text, '₹')]";
            String actualPriceLocator = currentPriceLocator + "/preceding-sibling::android.widget.TextView[last()]";
            String discountLocator = parentCardLocator + "//android.widget.TextView[contains(@text, 'off')]";
            String insurancePlansDropdownLocator = parentCardLocator + "/android.view.ViewGroup[2]";
            String insurancePlansListLocator = "//android.widget.ScrollView//android.widget.TextView";

            String insuranceName   = driver.findElement(AppiumBy.xpath(insuranceNameLocator)).getText();
            String insuranceType   = driver.findElement(AppiumBy.xpath(insuranceTypeLocator)).getText();
            String currentPrice    = driver.findElement(AppiumBy.xpath(currentPriceLocator)).getText();
            String discount = "";
            String actualPrice = "";

            // Handling an insurance product with no discount
            if (driver.findElements(AppiumBy.xpath(discountLocator)).size() != 0) {
                discount = driver.findElement(AppiumBy.xpath(discountLocator)).getText();
                actualPrice = driver.findElement(AppiumBy.xpath(actualPriceLocator)).getText();
            }

            driver.findElement(AppiumBy.xpath(insurancePlansDropdownLocator)).click();
            List<WebElement> insurancePlanElements = driver.findElements(AppiumBy.xpath(insurancePlansListLocator));
            List<String> insurancePlans = insurancePlanElements
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
            insurancePlanElements.get(0).click();

            System.out.println("------------------------------------------------");
            System.out.println("Insurance Name: " + insuranceName);
            System.out.println("Insurance Type: " + insuranceType);
            System.out.println("Actual Price: " + actualPrice);
            System.out.println("Current Price: " + currentPrice);
            System.out.println("Discount: " + discount);
            System.out.println("Insurance Plans: " + insurancePlans);
            System.out.println("------------------------------------------------");

            // Avoid StaleElementReferenceException by re-assigning the value
            List<WebElement> cardElements = driver.findElements(AppiumBy.xpath(parentCardLocator));
            if (isTheLastInsuranceCardAvailable)
                break;

            driver.executeScript("mobile: flingGesture", ImmutableMap.of(
                    "elementId", ((RemoteWebElement) cardElements.get(0)).getId(),
                    "direction", "right",
//                    "percent", 0.75
                    "speed", 500
            ));
        }
    }
}
