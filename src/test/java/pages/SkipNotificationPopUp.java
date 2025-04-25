package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverUtils;
import utils.Utils;

import java.time.Duration;

public class SkipNotificationPopUp {

    AppiumDriver driver = DriverUtils.getDriver();

    public void tapSkipButton() {
        Utils.log("Handling 'Enable Notifications' popup");
        try {
            String textUiSelector = "new UiSelector().text(\"%s\")";
            By skipPopupButton = AppiumBy.androidUIAutomator(
                    String.format(textUiSelector, "Skip, I'll do it later"));

            WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement skipButton = popupWait.until(ExpectedConditions.elementToBeClickable(skipPopupButton));
            skipButton.click();
            Utils.log("Clicked 'Skip, I'll do it later' button");
        } catch (Exception e) {
            Utils.log("Could not find or click the 'Skip, I'll do it later' button: " + e.getMessage());
        }
    }
}
