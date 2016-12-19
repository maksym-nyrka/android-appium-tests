package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 5:45 PM.
 */
public class AndroidRingtonesFolderPage extends BasePage {

    public AndroidRingtonesFolderPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    public AndroidElement getFileElementByName(String name)
    {
        return driver.findElement(By.xpath("//android.widget.TextView[@text='"+name+"']"));
    }
}
