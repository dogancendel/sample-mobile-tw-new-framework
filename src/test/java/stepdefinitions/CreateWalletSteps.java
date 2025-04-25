package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;
import utils.Utils;

public class CreateWalletSteps {

    WelcomePage welcomePage = new WelcomePage();
    CreatePasscodePage createPasscodePage = new CreatePasscodePage();
    ConfirmPasscodePage confirmPasscodePage = new ConfirmPasscodePage();
    SkipNotificationPopUp notificationsPage = new SkipNotificationPopUp();
    HomePage homePage = new HomePage();
    WalletPage walletPage = new WalletPage();
    String walletName;

    @Given("Trust Wallet application is opened")
    public void openApp() {
        // App açıldığında yapılacak işlemler burada olabilir. Şu an boş bırakılmış.
    }

    @When("User clicks on {string} button")
    public void userClicksCreateWallet(String btn) {
        welcomePage.clickCreateNewWallet();
    }

    @And("{string} page is displayed")
    public void pageDisplayed(String page) {
        switch (page) {
            case "Create passcode":
                Assert.assertTrue(createPasscodePage.isCreatePasscodePageDisplayed(), page + " page is not displayed!");
                break;
            case "Confirm passcode":
                Assert.assertTrue(confirmPasscodePage.isConfirmPasscodePageDisplayed(), page + " page is not displayed!");
                break;
            default:
                throw new IllegalArgumentException("Unsupported page: " + page);
        }
    }

    @And("User enters the passcode")
    public void userEntersPasscode() {
        createPasscodePage.enterPasscode("654321");
    }

    @And("User confirms the passcode")
    public void confirmPasscode() {
        confirmPasscodePage.enterPasscode("654321");
    }

    @And("Notifications pop-up appears and user taps the skip button")
    public void skipNotificationPopUp() {
        notificationsPage.tapSkipButton();
    }

    @And("User skips crypto deposit screen")
    public void skipCryptoDeposit() {
        homePage.skipCryptoDepositScreen();
    }

    @And("User navigates to Wallets page")
    public void navigateWallets() {
        walletName = homePage.getToWalletName();
        Assert.assertFalse(walletName.isEmpty(),"Home page did not load successfully (Wallet name is empty).");
        Utils.log("Home page loaded successfully. Wallet name: " + walletName);
        homePage.clickWalletName();
    }

    @Then("User verifies the wallet name on the Wallet page")
    public void verifyWalletName() {
        walletPage.compareWalletName(walletName);
    }
}
