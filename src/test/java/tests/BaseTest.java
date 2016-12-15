package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created on 13-Dec-16 at 5:10 PM.
 */
abstract public class BaseTest {

    AppiumDriver<AndroidElement> driver;
    private AppiumDriverLocalService localService;
    private String apkFilePath;
    private String appActivity;
    private String sdkPath = "C:\\Users\\user001\\AppData\\Local\\Android\\sdk\\";
    private String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
    private String emulatorPath = sdkPath + "tools" + File.separator + "emulator";

    BaseTest(String filepath, String activity)
    {
        this.apkFilePath=filepath;
        this.appActivity=activity;
    }
    @BeforeClass
    public void setUp() throws MalformedURLException {

        String[] startEmulatorCommand = new String[] { emulatorPath, "-avd", "Nexus5-Android6" };
        //String[] startCommand = new String[] { emulatorPath, "adb -s emulator-5554 -e install -r ch.protonmail.android_1.5.7-251_minAPI15(arm64-v8a,armeabi,armeabi-v7a,x86)(nodpi)_apkmirror.com.apk" };
        //String[] wipeDataCommand = new String[] { emulatorPath, "-avd", "Nexus5-Android6","-wipe-data" };
        try {
            new ProcessBuilder(startEmulatorCommand).start();
            //new ProcessBuilder(wipeDataCommand).start();
            //new ProcessBuilder(startCommand).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        localService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:\\Program Files (x86)\\Appium\\node.exe"))
                .withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js")));
        localService.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "6.0");
        capabilities.setCapability("deviceName","Nexus5-Android6");
        capabilities.setCapability("platformName","Android");

        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("app", apkFilePath);
        capabilities.setCapability("app-wait-activity", appActivity);
        capabilities.setCapability("newCommandTimeout","12000");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @AfterClass
    public void teardown(){
        driver.quit();
        localService.stop();
        /*
        String[] killEmuCommand = new String[] { adbPath, "emu", "kill" };
        try {
            new ProcessBuilder(killEmuCommand).start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void adbSwipe(int startX, int startY, int endX, int endY, int mills)
    {
        try {
            Runtime.getRuntime().exec("adb shell input swipe "+startX+" "+startY+" "+endX+" "+endY+" "+mills);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void adbTap(int tapX, int tapY)
    {
        try {
            Runtime.getRuntime().exec("adb shell input tap "+tapX+" "+tapY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
