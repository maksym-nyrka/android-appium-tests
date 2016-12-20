package tests;

import actions.AndroidActions;
import actions.MailActions;
import data_providers.DataProviders;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Created on 19-Dec-16 at 1:25 PM.
 */
public class MailWithAttachmentTest extends BaseTest {

    private InboxPage objInboxPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private NewLetterPage objNewLetterPage;
    private MessagePage objMessagePage;
    private AndroidHomePage objAndroidHomePage;
    private AndroidCameraPage objAndroidCameraPage;
    private DraftsPage objDraftsPage;
    private AndroidFileAttachPage objAndroidFileAttachPage;

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
        MailActions.handleGoogleStuff(objInboxPage);
        MailActions.startComposingNewMail(objInboxPage);

        objNewLetterPage = new NewLetterPage(driver);
        MailActions.newMailInit(objNewLetterPage);
        MailActions.setBody(mailBody,objNewLetterPage);

        MailActions.navigateUp(objNewLetterPage);
        //driver.navigate().back();
        //objNewLetterPage.adbKeyevent(AndroidKeyCode.BACK);
        MailActions.saveAsDraft(objNewLetterPage);

        objInboxPage = new InboxPage(driver);
        MailActions.openMenu(objInboxPage);
        MailActions.clickDraftsMenu(objInboxPage);
        //objInboxPage.clickDraftsMenuButton();

        objDraftsPage = new DraftsPage(driver);
        String draftsTitleXpath="//android.widget.TextView[@text='DRAFTS']";
        objDraftsPage.waitElementToBeVisible(By.xpath(draftsTitleXpath));
        MailActions.selectFirstMessageDrafts(objDraftsPage);

        objNewLetterPage = new NewLetterPage(driver);
        MailActions.newMailInit(objNewLetterPage);
        //String bodyXpath="//android.widget.EditText[@index='1']";
        //objNewLetterPage.waitElementToBeVisible(By.xpath(bodyXpath));
        Assert.assertEquals(objNewLetterPage.getBodyTextField(),mailBody);

        AndroidActions.home(objNewLetterPage);

        objAndroidHomePage = new AndroidHomePage(driver);
        AndroidActions.waitMainMenuIsOpened(objAndroidHomePage);
        AndroidActions.openCamera(objAndroidHomePage);

        objAndroidCameraPage = new AndroidCameraPage(driver);
        AndroidActions.takePicture(objAndroidCameraPage);


        objAndroidCameraPage.adbLaunchAppActivity();
        objNewLetterPage = new NewLetterPage(driver);
        MailActions.newMailInit(objNewLetterPage);
        MailActions.clickAttachments(objNewLetterPage);
        MailActions.clickAttachFile(objNewLetterPage);

        objAndroidFileAttachPage = new AndroidFileAttachPage(driver);
        //AndroidActions.clickAttachImage(objAndroidFileAttachPage);
        AndroidActions.attachFirstImage(objAndroidFileAttachPage);

        objNewLetterPage = new NewLetterPage(driver);
        String attachXpath="//android.widget.TextView[@text='Add attachment']";
        objNewLetterPage.waitElementToBeVisible(By.xpath(attachXpath));
        String expected = objNewLetterPage.getAttachmentName();

        MailActions.navigateUp(objNewLetterPage);
        MailActions.setRecipient(username,objNewLetterPage);
        MailActions.setSubject(mailSubject,objNewLetterPage);
        Assert.assertEquals(objNewLetterPage.getAttachmentCount(),1);

        MailActions.sendMail(objNewLetterPage);

        objInboxPage = new InboxPage(driver);
        MailActions.refreshInbox(objInboxPage);
        MailActions.selectFirstMessageInbox(objInboxPage);

        objMessagePage = new MessagePage(driver);
        MailActions.clickAttachment(objMessagePage);
        Assert.assertEquals(objMessagePage.getAttachmentName().trim(),expected.trim());
        //Assert.assertEquals(objMessagePage.getSenderTextField().getText().toLowerCase(),username.substring(0,12).toLowerCase());
    }

}
