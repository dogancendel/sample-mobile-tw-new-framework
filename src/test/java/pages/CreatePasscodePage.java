package pages;

public class CreatePasscodePage extends BasePage {

    public void enterPasscode(String passcode) {
        enterPasscodeDigits(passcode);
    }

    public boolean isCreatePasscodePageDisplayed() {
        return isPageDisplayedByText("Create passcode", "Create passcode");
    }
}
