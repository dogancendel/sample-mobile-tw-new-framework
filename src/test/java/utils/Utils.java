package utils;

import org.apache.commons.io.FileUtils;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Utils {

    public static void waitForElementVisible(WebElement element, Duration timeout) {
        Awaitility.await().atMost(timeout).until(element::isDisplayed);
    }

    public static WebElement waitForVisibility(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverUtils.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element not visible after " + timeoutInSeconds + " seconds: " + e.getMessage());
            return null;
        }
    }

    public static void log(String msg) {
        System.out.println("[LOG] " + msg);
    }

    public static void captureScreenshot(String stepName) {
        File srcFile = ((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.FILE);
        String fileName = "screenshots/" + stepName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
