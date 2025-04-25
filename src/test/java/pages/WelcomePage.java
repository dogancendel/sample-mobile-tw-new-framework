package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import utils.DriverUtils;
import utils.Utils;

import java.time.Duration;

public class WelcomePage {

    AppiumDriver driver = DriverUtils.getDriver();

    public void clickCreateNewWallet() {

        WebElement btn = driver.findElement(AppiumBy.id("CreateNewWalletButton"));
        Utils.waitForElementVisible(btn, Duration.ofSeconds(10));
        btn.click();
        Utils.log("Clicked on 'Create New Wallet'");
    }
}
