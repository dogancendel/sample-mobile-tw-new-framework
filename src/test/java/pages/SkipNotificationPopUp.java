package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.internal.Utils;

public class SkipNotificationPopUp extends BasePage {

    public void tapSkipButton() {
        Utils.log("Handling 'Enable Notifications' popup");
        By skipButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Skip, I'll do it later\")");
        WebElement button = waitForElementToBeClickable(skipButton, 10);
        if (button != null) {
            click(button);
            Utils.log("Clicked 'Skip, I'll do it later' button");
        } else {
            Utils.log("Notification popup not found.");
        }
    }
}
