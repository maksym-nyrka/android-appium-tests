package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 19-Dec-16 at 3:53 PM.
 */
public class AndroidFileAttachPage extends BasePage {

    @FindBy(xpath = "//android.widget.TextView[@text='Images']")
    AndroidElement imagesButton;

    @FindBy(xpath = "//android.widget.GridView[@index='0']//android.widget.FrameLayout[@index='0']")
    AndroidElement cameraFolderButton;

    @FindBy(xpath = "//android.widget.GridView[@index='0']//android.widget.FrameLayout[@index='0']")
    AndroidElement firstImage;

    public AndroidFileAttachPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public void clickImagesButton()
    {
        imagesButton.click();
    }
    public void clickCameraFolderButton()
    {
        cameraFolderButton.click();
    }

    public void clickFirstImage()
    {
        firstImage.click();
    }
}
