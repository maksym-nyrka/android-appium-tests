package actions;

import org.openqa.selenium.By;
import pages.InboxPage;
import pages.LoginPage;
import pages.MainPage;
import pages.NewLetterPage;

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
        inboxPage.clickMenuButton();
        inboxPage.clickInboxMenuButton();
    }
    public static void selectFirstMessage(InboxPage inboxPage)
    {
        inboxPage.clickFirstMessage();
    }

    //New Letter Page
    public static void composeNewMail(String recipient, String subject, String body, NewLetterPage newLetterPage)
    {
        String denyButtonXpath="//android.widget.Button[@text='Deny']";
        newLetterPage.waitElementToBeClickable(By.xpath(denyButtonXpath));
        newLetterPage.clickDenyAccessButton();
        newLetterPage.setRecipientTextField(recipient);
        newLetterPage.setSubjectTextField(subject);
        newLetterPage.setBodyTextField(body);
    }
    public static void setSubject(String subject, NewLetterPage newLetterPage)
    {
        newLetterPage.setSubjectTextField(subject);
    }
    public static void sendMail(NewLetterPage newLetterPage)
    {
        newLetterPage.clickSendMailButton();
    }
}
