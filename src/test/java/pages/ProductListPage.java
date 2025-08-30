package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class ProductListPage {
    private final AndroidDriver driver;

    // IDs de la grilla
    private static final String PRODUCTS_RV_ID = "com.saucelabs.mydemoapp.android:id/productRV";
    private static final String TITLE_TV_ID    = "com.saucelabs.mydemoapp.android:id/titleTV";

    public ProductListPage(AndroidDriver driver) { this.driver = driver; }

    public void waitForGalleryLoaded() {
        driver.findElement(AppiumBy.id(PRODUCTS_RV_ID));
    }

    public void openProduct(String productName) {

        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"" + TITLE_TV_ID + "\")" +
                            ".text(\"" + productName + "\"))")).click();
            return;
        } catch (Exception ignore) { }


        WebElement el = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"" + TITLE_TV_ID + "\")" +
                        ".textContains(\"" + productName.replace("-", "").replace("  ", " ").trim().split(" ")[2] + "\"))"
        ));
        el.click();
    }
}
