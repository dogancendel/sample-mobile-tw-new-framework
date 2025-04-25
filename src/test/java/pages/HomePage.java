package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.internal.Utils;

public class HomePage extends BasePage {

    private final By walletNameText = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"topBarWalletName\")");

    public void skipCryptoDepositScreen() {
        Utils.log("Handling 'Crypto Deposit Screen' popup");
        By skipButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Skip, I'll do it later\")");
        WebElement button = waitForElementToBeClickable(skipButton, 10);
        if (button != null) {
            click(button);
            Utils.log("Clicked 'Skip, I'll do it later' button");
        } else {
            Utils.log("Skip popup not found.");
        }
    }

    public String getToWalletName() {
        Utils.log("Getting wallet name");
        return getText(walletNameText);
    }

    public void clickWalletName() {
        click(walletNameText);
    }
}
