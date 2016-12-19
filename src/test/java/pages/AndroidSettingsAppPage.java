package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 5:12 PM.
 */
public class AndroidSettingsAppPage extends BasePage {

    @FindBy(xpath = "//android.widget.TextView[@text='Storage & USB']")
    AndroidElement storageAndUSBButton;

    public AndroidSettingsAppPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    public void clickStorageAndUSBButton()
    {
        storageAndUSBButton.click();
    }
}
