package tests;

import actions.AndroidActions;
import data_providers.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

/**
 * Created on 16-Dec-16 at 4:34 PM.
 */
public class FileEditingTest extends BaseTest {

    private AndroidHomePage objAndroidHomePage;
    private AndroidAppMenuPage objAndroidAppMenuPage;
    private AndroidSettingsAppPage objAndroidSettingsAppPage;
    private AndroidSettingsStoragePage objAndroidSettingsStoragePage;
    private AndroidInternalStoragePage objAndroidInternalStoragePage;
    private AndroidRingtonesFolderPage objAndroidRingtonesFolderPage;
    private AndroidHTMLViewerPage objAndroidHTMLViewerPage;

    private final String settingsText = "Settings";
    private final String exploreText = "Explore";
    private final String ringtonesText = "Ringtones";

    @Test(dataProvider = "file", dataProviderClass = DataProviders.class)
    public void fileTest(String fileContent, String fileName, String filePath)
    {
        forceStopApp();
        AndroidActions.createFile(fileName,fileContent);
        objAndroidHomePage = new AndroidHomePage(driver);
        objAndroidHomePage.adbPush(fileName,filePath);
        String clocksId="com.android.deskclock:id/analog_appwidget";
        objAndroidHomePage.waitElementToBeVisible(By.id(clocksId));
        AndroidActions.openAppsMenu(objAndroidHomePage);

        objAndroidAppMenuPage = new AndroidAppMenuPage(driver);
        objAndroidAppMenuPage.scrollToElementDown(settingsText,objAndroidAppMenuPage.getRecyclerView());
        AndroidActions.openSettingsApp(objAndroidAppMenuPage);

        objAndroidSettingsAppPage = new AndroidSettingsAppPage(driver);
        String storageAndUSBXpath="//android.widget.TextView[@text='Storage & USB']";
        objAndroidSettingsAppPage.waitElementToBeVisible(By.xpath(storageAndUSBXpath));
        AndroidActions.openStorageAndUSB(objAndroidSettingsAppPage);

        objAndroidSettingsStoragePage = new AndroidSettingsStoragePage(driver);
        objAndroidSettingsStoragePage.scrollToElementDown(exploreText,objAndroidSettingsStoragePage.getListView());
        AndroidActions.openExplore(objAndroidSettingsStoragePage);

        objAndroidInternalStoragePage = new AndroidInternalStoragePage(driver);
        objAndroidInternalStoragePage.scrollToElementDown(ringtonesText,objAndroidInternalStoragePage.getListView());
        AndroidActions.openRingtonesFolder(objAndroidInternalStoragePage);

        objAndroidRingtonesFolderPage = new AndroidRingtonesFolderPage(driver);
        AndroidActions.openFileByName(fileName,objAndroidRingtonesFolderPage);

        objAndroidHTMLViewerPage = new AndroidHTMLViewerPage(driver);
        String textXpath = "//android.webkit.WebView[@index='0']//android.view.View[@index='0']";
        objAndroidHTMLViewerPage.waitElementToBeVisible(By.xpath(textXpath));
        objAndroidHTMLViewerPage.adbPull(fileName,filePath);
        Assert.assertEquals(objAndroidHTMLViewerPage.getTextViewText().trim(),AndroidActions.readFile(fileName).trim());


        //Change text
        driver.navigate().back();
        objAndroidRingtonesFolderPage = new AndroidRingtonesFolderPage(driver);
        AndroidActions.editFile(fileName,fileContent.toUpperCase());
        objAndroidRingtonesFolderPage.adbPush(fileName,filePath);

        objAndroidRingtonesFolderPage = new AndroidRingtonesFolderPage(driver);
        AndroidActions.openFileByName(fileName,objAndroidRingtonesFolderPage);

        objAndroidHTMLViewerPage = new AndroidHTMLViewerPage(driver);
        objAndroidHTMLViewerPage.waitElementToBeVisible(By.xpath(textXpath));
        objAndroidHTMLViewerPage.adbPull(fileName,filePath);
        Assert.assertEquals(objAndroidHTMLViewerPage.getTextViewText().trim(),AndroidActions.readFile(fileName).trim());
    }
}
