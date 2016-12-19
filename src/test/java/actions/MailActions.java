package actions;

import org.openqa.selenium.By;
import pages.*;

import java.io.InputStream;

/**
 * Created on 16-Dec-16 at 10:59 AM.
 */
public class MailActions {

    //Main Page
    public static boolean userIsNotLoggedIn(MainPage mainPage)
    {
        return mainPage.getSignInButtonListSize() > 0;
    }
    public static void logIn(String username, String password, LoginPage loginPage)
    {
        loginPage.setUsernameTextField(username);
        loginPage.setPasswordTextField(password);
        loginPage.clickLoginButton();
    }

    //Inbox Page
    public static void handleGoogleStuff(InboxPage inboxPage)
    {
        inboxPage.clickGetGooglePlayServicesButton();
    }
    public static void startComposingNewMail(InboxPage inboxPage)
    {
        inboxPage.clickComposeMailButton();
    }
    public static void refreshInbox(InboxPage inboxPage)
    {
        String menuButtonXpath="//android.widget.ImageButton[@content-desc='Open']";
        inboxPage.waitElementToBeVisible(By.xpath(menuButtonXpath));
        inboxPage.clickMenuButton();
        inboxPage.clickInboxMenuButton();
    }
    public static void openMenu(InboxPage inboxPage)
    {
        String menuButtonXpath="//android.widget.ImageButton[@content-desc='Open']";
        inboxPage.waitElementToBeVisible(By.xpath(menuButtonXpath));
        inboxPage.clickMenuButton();
    }
    public static void openDrafts(InboxPage inboxPage)
    {
        inboxPage.clickDraftsMenuButton();
    }
    public static void selectFirstMessageInbox(InboxPage inboxPage)
    {
        String inboxTitleXpath="//android.widget.TextView[@text='INBOX']";
        inboxPage.waitElementToBeVisible(By.xpath(inboxTitleXpath));
        inboxPage.clickFirstMessage();
    }
    public static void clickDraftsMenu(InboxPage inboxPage)
    {
        String draftsXpath="//android.widget.TextView[@text='Drafts']";
        inboxPage.waitElementToBeVisible(By.xpath(draftsXpath));
        inboxPage.clickDraftsMenuButton();
    }
    //New Letter Page
    public static void newMailFullRoutine(String recipient, String subject, String body, NewLetterPage newLetterPage)
    {
        String denyButtonXpath="//android.widget.Button[@text='Deny']";
        newLetterPage.waitElementToBeClickable(By.xpath(denyButtonXpath));
        newLetterPage.clickDenyAccessButton();
        newLetterPage.setRecipientTextField(recipient);
        newLetterPage.setSubjectTextField(subject);
        newLetterPage.setBodyTextField(body);
    }
    public static void setRecipient(String recipient, NewLetterPage newLetterPage)
    {
        String recipientXpath="//android.widget.MultiAutoCompleteTextView[@index='1']";
        newLetterPage.waitElementToBeVisible(By.xpath(recipientXpath));
        newLetterPage.setRecipientTextField(recipient);
    }
    public static void setSubject(String subject, NewLetterPage newLetterPage)
    {
        newLetterPage.setSubjectTextField(subject);
    }
    public static void setBody(String body, NewLetterPage newLetterPage)
    {
        newLetterPage.setBodyTextField(body);
    }
    public static void sendMail(NewLetterPage newLetterPage)
    {
        newLetterPage.clickSendMailButton();
    }
    public static void newMailInit(NewLetterPage newLetterPage)
    {
        String denyButtonXpath="//android.widget.Button[@text='Deny']";
        newLetterPage.waitElementToBeClickable(By.xpath(denyButtonXpath));
        newLetterPage.clickDenyAccessButton();
    }
    public static void saveAsDraft(NewLetterPage newLetterPage)
    {
        newLetterPage.clickYesSaveAsDraft();
    }
    public static void navigateUp(NewLetterPage newLetterPage)
    {
        String buttonXpath="//android.widget.ImageButton[@content-desc='Navigate up']";
        newLetterPage.waitElementToBeVisible(By.xpath(buttonXpath));
        newLetterPage.clickNavigateUpButton();
    }
    public static void clickAttachments(NewLetterPage newLetterPage)
    {
        newLetterPage.clickAttachmentButton();
    }
    public static void clickAttachFile(NewLetterPage newLetterPage)
    {
        newLetterPage.clickAttachFileButton();
    }

    //Drafts Page
    public static void selectFirstMessageDrafts(DraftsPage draftsPage)
    {
        draftsPage.clickFirstMessage();
    }

    //Message Page
    public static void clickAttachment(MessagePage messagePage)
    {
        String xpath="//android.widget.TextView[contains(@text,'1 Attachment')]";
        messagePage.waitElementToBeVisible(By.xpath(xpath));
        messagePage.clickAttachmentButton();
    }

}
