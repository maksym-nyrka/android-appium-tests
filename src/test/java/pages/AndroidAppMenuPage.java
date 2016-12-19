package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 4:50 PM.
 */
public class AndroidAppMenuPage extends BasePage {

    @FindBy(xpath = "//android.support.v7.widget.RecyclerView[@index='0']")
    AndroidElement recyclerView;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Settings']")
    AndroidElement settingsApp;

    public AndroidAppMenuPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public AndroidElement getRecyclerView() {
        return recyclerView;
    }
    public void clickSettingsApp()
    {
        settingsApp.click();
    }
}
