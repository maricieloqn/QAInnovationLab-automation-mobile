package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final String PLUS_ID     = "com.saucelabs.mydemoapp.android:id/plusIV";
    private static final String ADD_CART_ID = "com.saucelabs.mydemoapp.android:id/cartBt";

    public ProductDetailPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void setUnits(int units) {
        if (units <= 1) return;
        // Asegura que el + esté visible
        try {
            driver.findElement(AppiumBy.id(PLUS_ID));
        } catch (Exception e) {
            scrollToAddToCartZone(); // la zona de qty está junto al botón
        }
        WebElement plus = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id(PLUS_ID)));
        for (int i = 1; i < units; i++) plus.click();
    }

    public void addToCart() {
        scrollToAddToCartZone();
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id(ADD_CART_ID)));
        btn.click();
    }

    private void scrollToAddToCartZone() {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().resourceId(\"" + ADD_CART_ID + "\"))"));
        } catch (Exception ignored) {}
    }
}
