package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;


/**
 * Created on 16-Dec-16 at 11:01 AM.
 */
public class MainPage extends BasePage {


    @FindBy(xpath = "//android.widget.Button[@text='Sign In']")
    AndroidElement signinButton;

    public MainPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public int getSignInButtonListSize()
    {
        return driver.findElements(By.xpath("//android.widget.Button[@text='Sign In']")).size();
    }
    public void clickSingInButton()
    {
        signinButton.click();
    }
}
