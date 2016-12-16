package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 11:01 AM.
 */
public class InboxPage extends BasePage {

    @FindBy(xpath = "//android.widget.Button[@text='close tour']")
    AndroidElement closeTourButton;

    @FindBy(xpath = "//android.widget.Button[@text='Get Google Play services']")
    AndroidElement googlePlayServicesButton;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Compose message']")
    AndroidElement composeMailButton;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Open']")
    AndroidElement menuButton;

    @FindBy(xpath = "//android.widget.TextView[@text='Inbox']")
    AndroidElement inboxMenuButton;

    @FindBy(xpath = "//android.widget.ListView[@index='0']//android.widget.RelativeLayout[@index='1']")
    AndroidElement firstMessage;

    public InboxPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    public void clickCloseTourButton()
    {
        closeTourButton.click();
    }
    public void clickGetGooglePlayServicesButton()
    {
        googlePlayServicesButton.click();
    }
    public void clickComposeMailButton()
    {
        composeMailButton.click();
    }
    public void clickMenuButton()
    {
        menuButton.click();
    }
    public void clickInboxMenuButton()
    {
        inboxMenuButton.click();
    }
    public void clickFirstMessage() {
        firstMessage.click();
    }
}
