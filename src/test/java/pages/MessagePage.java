package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 12:16 PM.
 */
public class MessagePage extends BasePage {

    @FindBy(xpath = "//android.widget.TableRow[@index='0']//android.widget.TextView[@index='1']")
    AndroidElement senderTextField;

    public MessagePage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public AndroidElement getSenderTextField() {
        return senderTextField;
    }
}
