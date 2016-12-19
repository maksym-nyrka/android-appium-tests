package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 5:27 PM.
 */
public class AndroidSettingsStoragePage extends BasePage {

    @FindBy(xpath = "//android.widget.FrameLayout[@index='1']//android.widget.ListView[@index='0']")
    AndroidElement listView;

    @FindBy(xpath = "//android.widget.TextView[@text='Explore']")
    AndroidElement exploreButton;

    public AndroidSettingsStoragePage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public AndroidElement getListView() {
        return listView;
    }

    public void clickExploreButton()
    {
        exploreButton.click();
    }
}
