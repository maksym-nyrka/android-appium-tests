package tests; /**
 * Created on 15-Dec-16 at 2:27 PM.
 */
import data_providers.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.text.SimpleDateFormat;
import java.util.Date;

//This code is a masterpiece
public class MailAdbScrollTapTest extends BaseTest{

    private String mailSubject="Hello, tester";
    private String mailBody="How are you?";

    MailAdbScrollTapTest()
    {
        super("C:\\Users\\user001\\Downloads\\ch.protonmail.android_1.5.7-251_minAPI15(arm64-v8a,armeabi,armeabi-v7a,x86)(nodpi)_apkmirror.com.apk"
                ,"ch.protonmail.android.activities.SplashActivity");
    }

    @Test(dataProvider = "user-credentials", dataProviderClass = DataProviders.class)
    public void testMail(String username, String password)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //Main Page - if it's a first run
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='Get Google Play services']")));
        adbTap(500,1100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='Compose message']")));
        adbTap(1000,100);

        //New letter Page
        String dateNow = new SimpleDateFormat("K:mm aa").format(new Date());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='Deny']")));
        adbTap(550,1100);
        WebElement recipientTextfield = driver.findElement(By.xpath("//android.widget.MultiAutoCompleteTextView[@index='1']"));
        recipientTextfield.sendKeys(username);
        WebElement subjectTextfield = driver.findElement(By.xpath("//android.widget.EditText[@text='Subject']"));
        subjectTextfield.sendKeys(mailSubject);
        WebElement bodyTextfield = driver.findElement(By.xpath("//android.widget.EditText[@index='1']"));
        bodyTextfield.sendKeys(mailBody);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='Send']")));
        adbTap(1000,150);

        //Inbox Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageButton[@content-desc='Open']")));
        adbSwipe(100,500,100,1300,800);

        WebElement firstMessageTime = driver.findElement(By.xpath("//android.widget.TextView[@text='"+dateNow+"']"));
        Assert.assertEquals(firstMessageTime.getText(),dateNow);
    }


}

