package tests;
/**
 * Created on 15-Dec-16 at 2:27 PM.
 */
import actions.MailActions;
import data_providers.DataProviders;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;


import java.text.SimpleDateFormat;
import java.util.Date;

//This code is a masterpiece
public class MailAdbScrollTapTest extends BaseTest{
    private InboxPage objInboxPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private NewLetterPage objNewLetterPage;

    private String mailSubject="Hello, tester";
    private String mailBody="How are you?";

    @Test(dataProvider = "user-credentials", dataProviderClass = DataProviders.class)
    public void testMail(String username, String password)
    {
        objMainPage = new MainPage(driver);
        if (MailActions.userIsNotLoggedIn(objMainPage)) {
            objMainPage.clickSingInButton();

            objLoginPage = new LoginPage(driver);
            MailActions.logIn(username,password,objLoginPage);

            objInboxPage = new InboxPage(driver);
            String closeTourButtonXpath ="//android.widget.Button[@text='close tour']";
            objInboxPage.waitElementToBeClickable(By.xpath(closeTourButtonXpath));
            objInboxPage.clickCloseTourButton();
        }

        objInboxPage = new InboxPage(driver);
        String googleServicesButtonXpath="//android.widget.Button[@text='Get Google Play services']";
        objInboxPage.waitElementToBeVisible(By.xpath(googleServicesButtonXpath));
        objInboxPage.adbTap(500,1100);
        String composeMessageButtonXpath ="//android.widget.TextView[@content-desc='Compose message']";
        objInboxPage.waitElementToBeVisible(By.xpath(composeMessageButtonXpath));
        objInboxPage.adbTap(1000,100);

        objNewLetterPage = new NewLetterPage(driver);
        String dateNow = new SimpleDateFormat("K:mm aa").format(new Date());
        String denyAccessButtonXpath="//android.widget.Button[@text='Deny']";
        objNewLetterPage.waitElementToBeVisible(By.xpath(denyAccessButtonXpath));
        objNewLetterPage.adbTap(550,1100);
        MailActions.composeNewMail(username,mailSubject,mailBody,objNewLetterPage);
        String sendButtonXpath="//android.widget.TextView[@content-desc='Send']";
        objNewLetterPage.waitElementToBeVisible(By.xpath(sendButtonXpath));
        objNewLetterPage.adbTap(1000,150);

        objInboxPage = new InboxPage(driver);
        String menuXpath="//android.widget.ImageButton[@content-desc='Open']";
        objInboxPage.waitElementToBeVisible(By.xpath(menuXpath));
        objInboxPage.adbSwipe(100,500,100,1300,800);

        String firstMessageXpath ="//android.widget.TextView[@text='"+dateNow+"']";
        objInboxPage.waitElementToBeVisible(By.xpath(firstMessageXpath));
        AndroidElement firstMessageTime = driver.findElement(By.xpath("//android.widget.TextView[@text='"+dateNow+"']"));
        Assert.assertEquals(firstMessageTime.getText(),dateNow);
    }


}

