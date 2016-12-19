package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
    public void scrollToElementUp(String text, AndroidElement listViewElement)
    {
        List<MobileElement> textView = listViewElement.findElementsByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
        while (textView.size() == 0) {
            listViewElement.swipe(SwipeElementDirection.DOWN, 20, 15, 5000);
            textView = listViewElement.findElementsByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
        }
    }
    public void scrollToElementDown(String text, AndroidElement listViewElement)
    {
        List<MobileElement> textView = listViewElement.findElementsByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
        while (textView.size() == 0) {
            listViewElement.swipe(SwipeElementDirection.UP, 20, 15, 5000);
            textView = listViewElement.findElementsByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
        }
    }
    public void adbSwipe(int startX, int startY, int endX, int endY, int mills)
    {
        try {
            Runtime.getRuntime()
                    .exec("adb -s "+prop.getProperty("deviceName")
                            +" shell input swipe "+startX+" "+startY+" "+endX+" "+endY+" "+mills);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adbTap(int tapX, int tapY)
    {
        try {
            Runtime.getRuntime()
                    .exec("adb -s "+prop.getProperty("deviceName")
                            +" shell input tap "+tapX+" "+tapY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adbPush(String fileName, String filePath)
    {
        try {
            Runtime.getRuntime()
                    .exec("adb -s "+prop.getProperty("deviceName")
                            +" push "+fileName+" "+filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adbPull(String fileName, String filePath)
    {
        try {
            Runtime.getRuntime()
                    .exec("adb -s "+prop.getProperty("deviceName")
                            +" pull "+filePath+"/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adbKeyevent(int androidKeyCode)
    {
        try {
            Runtime.getRuntime()
                    .exec("adb -s "+prop.getProperty("deviceName")
                            +" shell input keyevent "+androidKeyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adbLaunchAppActivity()
    {
        //adb shell am start ch.protonmail.android/ch.protonmail.android.activities.SplashActivity
        try {
            Runtime.getRuntime()
                    .exec("adb -s "+prop.getProperty("deviceName")
                            +" shell am start "+prop.getProperty("packageName")+"/"+prop.getProperty("mainActivity"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
