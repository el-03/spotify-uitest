package drivers;

import config.ConfigConstructor;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidDriverInstance {

    public static AndroidDriver<AndroidElement> androidDriver;
    public static boolean IMECaptureStatus;
    public static String IMECaptureDir;

    public static void initialize(ConfigConstructor config) {
        String appiumUrl = config.getAppiumUrl();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", config.getDeviceName());
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UIAutomator2");
        caps.setCapability("udid", config.getUdid());
        caps.setCapability("appPackage", "com.spotify.music");
        caps.setCapability("appActivity", "com.spotify.music.MainActivity");
        caps.setCapability("noReset", true);
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("newCommandTimeout", 30);

        try {
            androidDriver = new AndroidDriver<AndroidElement>(new URL(appiumUrl), caps);
            androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            androidDriver.setSetting("imageMatchThreshold", config.getThresholdValue());
            IMECaptureStatus = Boolean.parseBoolean(config.getIMECapture());
            if (IMECaptureStatus) {
                String getDateTime = java.time.LocalDateTime.now().toString();
                File file = new File(System.getProperty("user.dir") + File.separator + "IMECaptureDir");
                if (!file.exists()) {
                    file.mkdir();
                    System.out.println("Creating director for IMECapture Assets...");
                }
                IMECaptureDir = System.getProperty("user.dir") + File.separator + "IMECaptureDir" + File.separator + getDateTime;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void quit() {
        androidDriver.quit();
    }

}
