package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.time.Duration;

public class WelcomePage extends BasePage {

    public void clickCreateNewWallet() {
        WebElement button = findElement(AppiumBy.id("CreateNewWalletButton"));
        Utils.waitForElementVisible(button, Duration.ofSeconds(10));
        click(button);
        Utils.log("Clicked on 'Create New Wallet'");
    }
}
