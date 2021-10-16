package hooks;

import config.ConfigConstructor;
import drivers.AndroidDriverInstance;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

public class AndroidDriverHooks {

    @Before(order = 0)
    public void initializeAndroidDriver() throws IOException {
        AndroidDriverInstance.initialize(new ConfigConstructor());
    }

    @After(order = 0)
    public void quitAndroidDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.attach(((TakesScreenshot) AndroidDriverInstance.androidDriver)
                            .getScreenshotAs(OutputType.BYTES), "image/png",
                    "invalid.jpg");
        }
        AndroidDriverInstance.quit();
    }
}
