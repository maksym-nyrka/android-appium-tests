package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 19-Dec-16 at 2:57 PM.
 */
public class DraftsPage extends BasePage {

    @FindBy(xpath = "//android.widget.ListView[@index='0']//android.widget.RelativeLayout[@index='1']")
    AndroidElement firstMessage;

    public DraftsPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    public void clickFirstMessage()
    {
        firstMessage.click();
    }
}
