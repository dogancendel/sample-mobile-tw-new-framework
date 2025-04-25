package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.internal.Utils;

public class WalletPage extends BasePage {

    private final By itemTitle = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"itemTitle\")");

    public void compareWalletName(String expectedWalletName) {
        Utils.log("Waiting for wallet name to appear on Wallet Page.");
        WebElement element = waitForVisibility(itemTitle, 10);
        if (element != null) {
            String actual = element.getText().trim();
            expectedWalletName = expectedWalletName.trim();
            Utils.log("Expected: " + expectedWalletName + " | Actual: " + actual);
            Assert.assertEquals(actual, expectedWalletName, "Wallet name verification failed.");
        } else {
            Assert.fail("'itemTitle' not visible on Wallet Page.");
        }
    }
}
