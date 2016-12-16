package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 16-Dec-16 at 11:02 AM.
 */
abstract public class BasePage {
    Properties prop = new Properties();
    AppiumDriver<AndroidElement> driver;
    public BasePage(AppiumDriver<AndroidElement> driver)
    {
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        try (InputStream inputStream = new FileInputStream("prop.properties")) {
            prop.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void waitElementToBeVisible(By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void waitElementToBeClickable(By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public void adbSwipe(int startX, int startY, int endX, int endY, int mills)
    {
        try {
            Runtime.getRuntime().exec("adb -s "+prop.getProperty("deviceName")+" shell input swipe "+startX+" "+startY+" "+endX+" "+endY+" "+mills);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adbTap(int tapX, int tapY)
    {
        try {
            Runtime.getRuntime().exec("adb -s "+prop.getProperty("deviceName")+" shell input tap "+tapX+" "+tapY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
