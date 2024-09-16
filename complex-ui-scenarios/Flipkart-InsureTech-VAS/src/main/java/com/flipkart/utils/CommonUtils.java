package com.flipkart.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class CommonUtils {

    public static void swipeUp(AppiumDriver driver) {

        Dimension screenSize = driver.manage().window().getSize();
        int startX;
        int endX;
        startX = endX = screenSize.getWidth() / 2;

        int startY = screenSize.getHeight() / 2;
        int endY = (int) (screenSize.getHeight() * 0.10);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence seqSwipe = new Sequence(finger, 1);
        seqSwipe.addAction(
                finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startX, startY)
        );
        seqSwipe.addAction(finger.createPointerDown(0));
        seqSwipe.addAction(
                finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY)
        );
        seqSwipe.addAction(finger.createPointerUp(0));
        driver.perform(List.of(seqSwipe));
    }
}