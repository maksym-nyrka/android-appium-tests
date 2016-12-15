package tests;

import data_providers.DataProviders;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;


/**
 * Created on 13-Dec-16 at 5:10 PM.
 */
public class MailWithScrollTest extends BaseTest{

    private String sdkPath = "C:\\Users\\user001\\AppData\\Local\\Android\\sdk\\";
    private String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
    private String emulatorPath = sdkPath + "tools" + File.separator + "emulator";


    private String mailSubject="Hello, tester";
    private String mailBody=mailSubject+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+"How are you?";

    MailWithScrollTest()
    {
        super("C:\\Users\\user001\\Downloads\\ch.protonmail.android_1.5.7-251_minAPI15(arm64-v8a,armeabi,armeabi-v7a,x86)(nodpi)_apkmirror.com.apk"
                ,"ch.protonmail.android.activities.SplashActivity");
    }

    @Test(dataProvider = "user-credentials", dataProviderClass = DataProviders.class)
    public void testMail(String username, String password)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //Main Page
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='Sign In']")));
        if (driver.findElements(By.xpath("//android.widget.Button[@text='Sign In']")).size()>0) {
            WebElement signinButton = driver.findElement(By.xpath("//android.widget.Button[@text='Sign In']"));
            signinButton.click();


            //Login Page
            WebElement usernameTextfield = driver.findElement(By.xpath("//android.widget.EditText[@text='Username']"));
            usernameTextfield.sendKeys(username);
            WebElement passwordTextfield = driver.findElement(By.xpath("//android.widget.EditText[@password='true']"));
            passwordTextfield.sendKeys(password);
            WebElement loginButton = driver.findElement(By.xpath("//android.widget.Button[@text='Sign In']"));
            loginButton.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='close tour']")));
            WebElement closeTourButton = driver.findElement(By.xpath("//android.widget.Button[@text='close tour']"));
            closeTourButton.click();
        }
        //Inbox Page
        /**/
        WebElement getGooglePlayServicesButton = driver.findElement(By.xpath("//android.widget.Button[@text='Get Google Play services']"));
        getGooglePlayServicesButton.click();
        WebElement composeMessageButton = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Compose message']"));
        composeMessageButton.click();

        //New letter Page
        WebElement denyAccessButton = driver.findElement(By.xpath("//android.widget.Button[@text='Deny']"));
        denyAccessButton.click();
        WebElement recipientTextfield = driver.findElement(By.xpath("//android.widget.MultiAutoCompleteTextView[@index='1']"));
        recipientTextfield.sendKeys(username);
        WebElement bodyTextfield = driver.findElement(By.xpath("//android.widget.EditText[@index='1']"));
        bodyTextfield.sendKeys(mailBody);
        driver.hideKeyboard();

        //Still: no pain - no gain
        /*Dimension screenSize = driver.manage().window().getSize();
        int startX = screenSize.getWidth() / 2;
        int endX = startX;
        int startY = (int) (screenSize.getHeight() * 0.70);
        int endY = (int) (screenSize.getHeight() * 0.20);
        AndroidDriver androidDriver = (AndroidDriver) driver; // WebDriver to AndroidDriver
        while(true) {
            // code to check with a text which is appears on the bottom of the page
            //break;
            //scroll(driver, startx, starty, endx, endy, 500);
            androidDriver.swipe(startX, startY, endX, endY, time);
        }*/
        /*
        AndroidElement list = driver.findElement(By.id("ch.protonmail.android:id/scroll_view"));//android.widget.ScrollView[@index='2']
        MobileElement subjectFieldElem = list
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                                + "new UiSelector().text(\"Subject\"));"));
        */


        //Scroll
        AndroidElement scrollView = driver.findElement(By.id("ch.protonmail.android:id/scroll_view"));
        List<MobileElement> textView = scrollView.findElementsByAndroidUIAutomator("new UiSelector().text(\"Subject\")");//android.widget.ScrollView[@index='2']

        while (textView.size() == 0) {
            System.out.println("Size : " + textView.size());
            scrollView.swipe(SwipeElementDirection.DOWN, 20, 15, 5000);
            textView = scrollView.findElementsByAndroidUIAutomator("new UiSelector().text(\"Subject\")");
        }

        WebElement subjectTextfield= driver.findElement(By.xpath("//android.widget.EditText[@text='Subject']"));
        subjectTextfield.sendKeys(mailSubject);

        WebElement sendMailButton = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Send']"));
        sendMailButton.click();

        //Inbox Page
        WebElement menuButton = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open']"));
        menuButton.click();
        WebElement inboxButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Inbox']"));
        inboxButton.click();
        WebElement firstMessage = driver.findElement(By.xpath("//android.widget.ListView[@index='0']//android.widget.RelativeLayout[@index='1']"));
        firstMessage.click();

        WebElement senderTextField = driver.findElement(By.xpath("//android.widget.TableRow[@index='0']//android.widget.TextView[@index='1']"));
        Assert.assertEquals(senderTextField.getText().toLowerCase(),username.substring(0,12).toLowerCase());
    }

}
