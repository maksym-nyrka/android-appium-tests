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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created on 13-Dec-16 at 5:10 PM.
 */
abstract public class BaseTest {
    Properties prop = new Properties();

    AppiumDriver<AndroidElement> driver;
    private AppiumDriverLocalService localService;
    private String sdkPath = "C:\\Users\\user001\\AppData\\Local\\Android\\sdk\\";
    private String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
    private String emulatorPath = sdkPath + "tools" + File.separator + "emulator";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        try (InputStream inputStream = new FileInputStream("prop.properties")) {
            prop.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String[] startEmulatorCommand = new String[] { emulatorPath, "-avd", "Nexus5-Android6" };
        //String[] startCommand = new String[] { emulatorPath, "adb -s emulator-5554 -e install -r ch.protonmail.android_1.5.7-251_minAPI15(arm64-v8a,armeabi,armeabi-v7a,x86)(nodpi)_apkmirror.com.apk" };
        //String[] wipeDataStartCommand = new String[] { emulatorPath, "-avd", "Nexus5-Android6","-wipe-data" };
        try {
            new ProcessBuilder(startEmulatorCommand).start();
            //new ProcessBuilder(wipeDataStartCommand).start();
            //new ProcessBuilder(startCommand).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        localService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(prop.getProperty("nodeExecPath")))
                .withAppiumJS(new File(prop.getProperty("appiumJSPath"))));
        localService.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "6.0");
        capabilities.setCapability("deviceName","Nexus5-Android6");
        capabilities.setCapability("platformName","Android");

        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("app", prop.getProperty("apkPath"));
        capabilities.setCapability("app-wait-activity", prop.getProperty("mainActivity"));
        capabilities.setCapability("newCommandTimeout","148800");

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
    public void forceStopApp()
    {
        try {
            Runtime.getRuntime().exec("adb -s "+prop.getProperty("deviceName")+" shell am force-stop "+prop.getProperty("packageName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
