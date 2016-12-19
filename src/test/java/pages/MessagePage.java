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

    @FindBy(xpath = "//android.widget.TextView[contains(@text,'1 Attachment')]")
    AndroidElement attachmentButton;

    @FindBy(xpath = "//android.widget.TextView[contains(@text,'IMG_')]")//android.widget.ScrollView[@index='2']//android.widget.LinearLayout[@index='5']
    AndroidElement attachmentName;

    public MessagePage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public AndroidElement getSenderTextField() {
        return senderTextField;
    }
    public void clickAttachmentButton()
    {
        attachmentButton.click();
    }
    public String getAttachmentName()
    {
        return attachmentName.getText();
    }
}
