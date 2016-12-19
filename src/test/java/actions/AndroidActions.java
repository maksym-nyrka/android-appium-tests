package actions;

import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 16-Dec-16 at 5:04 PM.
 */
public class AndroidActions {
    public static void home(BasePage basePage)
    {
        basePage.adbKeyevent(AndroidKeyCode.HOME);
    }
    //Android Home Page
    public static void openAppsMenu(AndroidHomePage androidHomePage)
    {
        androidHomePage.clickAppsMenuButton();
    }
    public static void openCamera(AndroidHomePage androidHomePage)
    {
        androidHomePage.clickCameraButton();
    }
    public static void waitMainMenuIsOpened(AndroidHomePage androidHomePage)
    {
        String clocksId="com.android.deskclock:id/analog_appwidget";
        androidHomePage.waitElementToBeVisible(By.id(clocksId));

    }
    //Android App Menu Page
    public static void openSettingsApp(AndroidAppMenuPage androidAppMenuPage)
    {
        androidAppMenuPage.clickSettingsApp();
    }

    //Android Settings App Page
    public static void openStorageAndUSB(AndroidSettingsAppPage androidSettingsAppPage)
    {
        androidSettingsAppPage.clickStorageAndUSBButton();
    }

    //Android Settings Storage Page
    public static void openExplore(AndroidSettingsStoragePage androidSettingsStoragePage)
    {
        androidSettingsStoragePage.clickExploreButton();
    }

    //Android Internal Storage Page
    public static void openRingtonesFolder(AndroidInternalStoragePage androidInternalStoragePage)
    {
        androidInternalStoragePage.clickRingtonesFolderButton();
    }

    //Android Ringtones Folder Page
    public static void openFileByName(String name, AndroidRingtonesFolderPage androidRingtonesFolderPage)
    {
        androidRingtonesFolderPage.getFileElementByName(name).click();
    }

    //Android Camera Page
    public static void takePicture(AndroidCameraPage androidCameraPage)
    {
        androidCameraPage.clickShutterButton();
    }

    //Android Attach File Page
    public static void clickAttachImage(AndroidFileAttachPage androidFileAttachPage)
    {
        androidFileAttachPage.clickImagesButton();
        androidFileAttachPage.clickCameraFolderButton();
    }
    public static void attachFirstImage(AndroidFileAttachPage androidFileAttachPage)
    {
        String xpath="//android.widget.GridView[@index='0']//android.widget.FrameLayout[@index='0']";
        androidFileAttachPage.waitElementToBeVisible(By.xpath(xpath));
        androidFileAttachPage.clickFirstImage();
    }

    //File actions
    public static String readFile(String fileName)
    {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()){
                sb.append(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public static void createFile(String fileName,String content)
    {
        List<String> lines = new ArrayList<>();
        new File(fileName);
        Path file = Paths.get(fileName);
        try {
            lines.add(content);
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void editFile(String fileName, String contentToAdd)
    {
        List<String> lines = new ArrayList<>();
        Path file = Paths.get(fileName);
        try {
            lines.add(contentToAdd);
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
