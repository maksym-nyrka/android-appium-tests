package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 19-Dec-16 at 2:23 PM.
 */
public class AndroidCameraPage extends BasePage {

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='Shutter button']")
    AndroidElement shutterButton;

    public AndroidCameraPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public void clickShutterButton()
    {
        shutterButton.click();
    }
}
