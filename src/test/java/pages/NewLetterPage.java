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

    @FindBy(xpath = "//android.widget.ScrollView[@index='2']//android.widget.FrameLayout[@index='2']//android.widget.EditText[@index='0']")
    AndroidElement subjectTextField;

    @FindBy(xpath = "//android.widget.EditText[@index='1']")
    AndroidElement bodyTextField;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Send']")
    AndroidElement sendMailButton;

    @FindBy(id = "ch.protonmail.android:id/scroll_view")
    AndroidElement scrollView;

    @FindBy(xpath = "//android.widget.Button[@text='Yes']")
    AndroidElement yesSaveAsDraft;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    AndroidElement navigateUpButton;

    @FindBy(xpath = "//android.widget.RelativeLayout[@index='4']//android.widget.LinearLayout[@index='4']")
    AndroidElement attachmentButton;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Attach file']")
    AndroidElement attachFileButton;

    @FindBy(id = "ch.protonmail.android:id/attachment_count")
    AndroidElement attachmentCount;

    @FindBy(xpath = "//android.widget.ListView[@index='4']//android.widget.LinearLayout[@index='1']/android.widget.TextView")
    AndroidElement attachmentName;

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

    public void clickYesSaveAsDraft()
    {
        yesSaveAsDraft.click();
    }

    public String getBodyTextField()
    {
        return bodyTextField.getText();
    }
    public void clickNavigateUpButton()
    {
        navigateUpButton.click();
    }
    public void clickAttachmentButton()
    {
        attachmentButton.click();
    }
    public void clickAttachFileButton()
    {
        attachFileButton.click();
    }
    public int getAttachmentCount()
    {
        return Integer.parseInt(attachmentCount.getText());
    }

    public String getAttachmentName() {
        return attachmentName.getText();
    }
}
