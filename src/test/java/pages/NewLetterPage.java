package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 11:02 AM.
 */
public class NewLetterPage extends BasePage {

    @FindBy(xpath = "//android.widget.Button[@text='Deny']")
    AndroidElement denyAccessButton;

    @FindBy(xpath = "//android.widget.MultiAutoCompleteTextView[@index='1']")
    AndroidElement recipientTextField;

    @FindBy(xpath = "//android.widget.EditText[@text='Subject']")
    AndroidElement subjectTextField;

    @FindBy(xpath = "//android.widget.EditText[@index='1']")
    AndroidElement bodyTextField;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Send']")
    AndroidElement sendMailButton;

    @FindBy(id = "ch.protonmail.android:id/scroll_view")
    AndroidElement scrollView;

    public NewLetterPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public void clickDenyAccessButton()
    {
        denyAccessButton.click();
    }
    public void setRecipientTextField(String recipient)
    {
        recipientTextField.sendKeys(recipient);
    }
    public void setSubjectTextField(String subject)
    {
        subjectTextField.sendKeys(subject);
    }
    public void setBodyTextField(String body)
    {
        bodyTextField.sendKeys(body);
    }
    public void clickSendMailButton()
    {
        sendMailButton.click();
    }
    public AndroidElement getScrollView() {
        return scrollView;
    }
}
