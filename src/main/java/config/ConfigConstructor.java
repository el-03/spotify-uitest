package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigConstructor {

    private final String appiumUrl;
    private final String deviceName;
    private final String udid;
    private final double thresholdValue;
    public String IMECapture;

    public ConfigConstructor() {

        String propFilePath = System.getProperty("user.dir") + "/src/main/java/config/config.properties";
        Properties p = new Properties();
        FileReader reader = null;

        try {
            reader = new FileReader(propFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.appiumUrl = p.getProperty("appiumUrl");
        this.deviceName = p.getProperty("deviceName");
        this.udid = p.getProperty("udid");
        this.thresholdValue = Double.parseDouble(p.getProperty("thresholdValue"));
        this.IMECapture = p.getProperty("imageMatcherElementCapture");

    }

    public String getAppiumUrl() {
        return appiumUrl;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getUdid() {
        return udid;
    }

    public String getIMECapture() {
        if (IMECapture == null || IMECapture.equals("")) {
            IMECapture = "false";
        }

        return IMECapture;
    }

    public double getThresholdValue() {
        return thresholdValue;
    }
}
