package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverUtils;
import utils.Utils;

import java.time.Duration;

public abstract class BasePage {

    protected AppiumDriver driver;

    public BasePage() {
        this.driver = DriverUtils.getDriver();
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        Utils.log("Clicking on element: " + locator);
        findElement(locator).click();
    }

    protected void click(WebElement element) {
        Utils.log("Clicking on element: " + element);
        element.click();
    }

    protected String getText(By locator) {
        WebElement element = waitForVisibility(locator, 10);
        return (element != null) ? element.getText() : "";
    }

    protected WebElement waitForVisibility(By locator, int seconds) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Utils.log("Element not visible: " + locator + " Error: " + e.getMessage());
            return null;
        }
    }

    protected WebElement waitForElementToBeClickable(By locator, int seconds) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            Utils.log("Element not clickable: " + locator + " Error: " + e.getMessage());
            return null;
        }
    }

    protected void enterPasscodeDigits(String passcode) {
        Utils.log("Entering passcode: " + passcode);
        for (char digit : passcode.toCharArray()) {
            By digitLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + digit + "\")");
            click(digitLocator);
        }
        Utils.log("Entered passcode successfully.");
    }

    protected boolean isPageDisplayedByText(String pageName, String expectedText) {
        By locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + expectedText + "\")");
        try {
            WebElement element = waitForVisibility(locator, 10);
            if (element != null && element.isDisplayed()) {
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
}
