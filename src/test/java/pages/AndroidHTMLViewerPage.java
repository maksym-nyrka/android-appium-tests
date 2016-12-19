package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created on 16-Dec-16 at 5:55 PM.
 */
public class AndroidHTMLViewerPage extends BasePage {

    @FindBy(xpath = "//android.webkit.WebView[@index='0']//android.view.View[@index='0']")
    AndroidElement textView;

    public AndroidHTMLViewerPage(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    public String getTextViewText()
    {
        return textView.getAttribute("name");
    }
}
