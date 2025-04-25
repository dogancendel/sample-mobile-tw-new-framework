package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.DriverUtils;
import utils.Utils;

public class WalletPage {

    AppiumDriver driver = DriverUtils.getDriver();

    public void compareWalletName(String expectedWalletName) {
        By itemTitleElement = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"itemTitle\")");
        Utils.log("Waiting for 'itemTitle' element to be visible on Wallet Page.");

        WebElement visibleItemTitle = Utils.waitForVisibility(itemTitleElement, 10);

        if (visibleItemTitle != null) {
            String actualWalletNameOnWalletPage = visibleItemTitle.getText().trim();
            expectedWalletName = expectedWalletName.trim();

            Utils.log("Actual wallet name on Wallet Page (trimmed): " + actualWalletNameOnWalletPage);
            Utils.log("Expected wallet name from Home Page (trimmed): " + expectedWalletName);

            Assert.assertEquals(actualWalletNameOnWalletPage, expectedWalletName, "Wallet name verification failed on Wallet Page.");
            Utils.log("Wallet name verification successful on Wallet Page.");
        } else {
            Utils.log("Error: 'itemTitle' element was not visible on Wallet Page within the specified timeout.");
            Assert.fail("'itemTitle' element was not visible on Wallet Page within the specified timeout.");
        }
    }

}