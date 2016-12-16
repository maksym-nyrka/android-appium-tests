package tests;

import actions.MailActions;
import data_providers.DataProviders;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;


/**
 * Created on 13-Dec-16 at 5:10 PM.
 */
public class MailScrollTest extends BaseTest{

    private InboxPage objInboxPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private NewLetterPage objNewLetterPage;
    private MessagePage objMessagePage;

    private String mailSubject="Hello, tester";
    private String mailBody=mailSubject+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+"How are you?";

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
        MailActions.handleGoogleStuff(objInboxPage);
        MailActions.startComposingNewMail(objInboxPage);

        objNewLetterPage = new NewLetterPage(driver);
        MailActions.composeNewMail(username,mailSubject,mailBody, objNewLetterPage);
        driver.hideKeyboard();

        //Scroll
        AndroidElement scrollView = objNewLetterPage.getScrollView();
        List<MobileElement> textView = scrollView.findElementsByAndroidUIAutomator("new UiSelector().text(\"Subject\")");
        while (textView.size() == 0) {
            scrollView.swipe(SwipeElementDirection.DOWN, 20, 15, 5000);
            textView = scrollView.findElementsByAndroidUIAutomator("new UiSelector().text(\"Subject\")");
        }

        MailActions.setSubject(mailSubject,objNewLetterPage);
        MailActions.sendMail(objNewLetterPage);

        objInboxPage = new InboxPage(driver);
        MailActions.refreshInbox(objInboxPage);
        MailActions.selectFirstMessage(objInboxPage);

        objMessagePage = new MessagePage(driver);
        Assert.assertEquals(objMessagePage.getSenderTextField().getText().toLowerCase(),username.substring(0,12).toLowerCase());
    }

}
