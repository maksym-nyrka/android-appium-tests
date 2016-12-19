package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 4:42 PM.
 */
public class AndroidHomePage extends BasePage {

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Apps']")
    AndroidElement appsMenuButton;

    @FindBy(xpath = "//android.widget.TextView[@text='Camera']")
    AndroidElement cameraButton;

    public AndroidHomePage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public void clickAppsMenuButton()
    {
        appsMenuButton.click();
    }
    public void clickCameraButton()
    {
        cameraButton.click();
    }
}
