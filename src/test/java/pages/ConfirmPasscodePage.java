package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverUtils;
import utils.Utils;

public class ConfirmPasscodePage {

    AppiumDriver driver = DriverUtils.getDriver();

    public void enterPasscode(String passcode) {
        Utils.log("Entering passcode: " + passcode);
        for (char digit : passcode.toCharArray()) {
            String uiSelector = "new UiSelector().text(\"" + digit + "\")";
            WebElement key = driver.findElement(AppiumBy.androidUIAutomator(uiSelector));
            key.click();
        }
        Utils.log("Entered passcode successfully.");
    }

    public boolean isPageDisplayedByText(String pageName, String expectedText) {
        By pageIdentifier = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + expectedText + "\")");
        try {
            WebElement pageTitleElement = Utils.waitForVisibility(pageIdentifier, 10);
            if (pageTitleElement != null && pageTitleElement.isDisplayed()) {
                Utils.log(pageName + " page is displayed successfully (text: '" + expectedText + "').");
                return true;
            } else {
                Utils.log(pageName + " page is not displayed (text: '" + expectedText + "')!");
                Utils.captureScreenshot(pageName.replaceAll("\\s+", "") + "PageNotDisplayed");
                return false;
            }
        } catch (Exception e) {
            Utils.log(pageName + " page is not displayed (text: '" + expectedText + "')! Error: " + e.getMessage());
            Utils.captureScreenshot(pageName.replaceAll("\\s+", "") + "PageNotDisplayed");
            return false;
        }
    }

    public boolean isConfirmPasscodePageDisplayed() {
        return isPageDisplayedByText("Confirm passcode", "Confirm passcode");
    }
}
