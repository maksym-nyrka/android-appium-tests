package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 11:01 AM.
 */
public class LoginPage extends BasePage {

    @FindBy(xpath = "//android.widget.EditText[@text='Username']")
    AndroidElement usernameTextField;

    @FindBy(xpath = "//android.widget.EditText[@password='true']")
    AndroidElement passwordTextField;

    @FindBy(xpath = "//android.widget.Button[@text='Sign In']")
    AndroidElement loginButton;

    public LoginPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public void setUsernameTextField(String username)
    {
        usernameTextField.sendKeys(username);
    }
    public void setPasswordTextField(String password)
    {
        passwordTextField.sendKeys(password);
    }
    public void clickLoginButton()
    {
        loginButton.click();
    }
}
