package tests;

import data_providers.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created on 13-Dec-16 at 5:10 PM.
 */
public class MailTest extends BaseTest{

    private String mailSubject="Hello, tester";
    private String mailBody="How are you?";

    MailTest()
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
        WebElement subjectTextfield = driver.findElement(By.xpath("//android.widget.EditText[@text='Subject']"));
        subjectTextfield.sendKeys(mailSubject);
        WebElement bodyTextfield = driver.findElement(By.xpath("//android.widget.EditText[@index='1']"));
        bodyTextfield.sendKeys(mailBody);
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
