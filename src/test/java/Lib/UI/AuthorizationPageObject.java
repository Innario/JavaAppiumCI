package Lib.UI;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find login button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click login button", 5);
    }

    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find  send keys to login field", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and send keys to password field", 5);
    }

    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click Submit button", 5);
    }
}
