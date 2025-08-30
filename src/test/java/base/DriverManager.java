package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            try {
                UiAutomator2Options opts = new UiAutomator2Options()
                        .setPlatformName("Android")
                        .setAutomationName("UiAutomator2")
                        .setUdid("emulator-5554")              // igual que ADB
                        .setDeviceName("Nexus S")              // texto libre
                        .setAppPackage("com.saucelabs.mydemoapp.android")
                        .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.MainActivity")
                        .setAppWaitActivity("*")
                        .amend("autoGrantPermissions", true)
                        .amend("noReset", true)
                        .amend("newCommandTimeout", 120);

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), opts);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            } catch (Exception e) {
                throw new RuntimeException("No se pudo iniciar el driver de Appium", e);
            }
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
