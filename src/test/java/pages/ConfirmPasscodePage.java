package pages;

public class ConfirmPasscodePage extends BasePage {

    public void enterPasscode(String passcode) {
        enterPasscodeDigits(passcode);
    }

    public boolean isConfirmPasscodePageDisplayed() {
        return isPageDisplayedByText("Confirm passcode", "Confirm passcode");
    }
}
