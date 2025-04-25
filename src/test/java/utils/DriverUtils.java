package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverUtils {
    private static AppiumDriver driver;

    public static void initializeDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "pixel-6-pro");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("disableIdLocatorAutocompletion", true);
        caps.setCapability("apps", System.getProperty("user.dir") + "/src/test/resources/apps/trustwallet.apk");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("newCommandTimeout", 100);
        caps.setCapability("androidInstallTimeout", 900000);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4725"), caps);
        } catch (Exception e) {
            throw new RuntimeException("Cannot initialize driver", e);
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) driver.quit();
    }
}
