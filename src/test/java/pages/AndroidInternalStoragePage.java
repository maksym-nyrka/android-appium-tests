package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 5:37 PM.
 */
public class AndroidInternalStoragePage extends BasePage {

    @FindBy(xpath = "//android.widget.ListView[@index='0']")
    AndroidElement listView;

    @FindBy(xpath = "//android.widget.TextView[@text='Ringtones']")
    AndroidElement ringtonesFolderButton;

    public AndroidInternalStoragePage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public AndroidElement getListView()
    {
        return listView;
    }

    public void clickRingtonesFolderButton()
    {
        ringtonesFolderButton.click();
    }
}
