package tests;

import actions.MailActions;
import data_providers.DataProviders;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;


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

    private String subjectName="Subject";

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
        MailActions.newMailInit(objNewLetterPage);
        MailActions.setRecipient(username,objNewLetterPage);
        MailActions.setBody(mailBody,objNewLetterPage);
        driver.hideKeyboard();

        objNewLetterPage.scrollToElementUp(subjectName,objNewLetterPage.getScrollView());

        MailActions.setSubject(mailSubject,objNewLetterPage);
        MailActions.sendMail(objNewLetterPage);

        objInboxPage = new InboxPage(driver);
        MailActions.refreshInbox(objInboxPage);
        MailActions.selectFirstMessageInbox(objInboxPage);

        objMessagePage = new MessagePage(driver);
        String mailSenderXpath="//android.widget.TableRow[@index='0']//android.widget.TextView[@index='1']";
        objMessagePage.waitElementToBeVisible(By.xpath(mailSenderXpath));
        Assert.assertEquals(objMessagePage.getSenderTextField().getText().toLowerCase(),username.substring(0,12).toLowerCase());
    }

}
