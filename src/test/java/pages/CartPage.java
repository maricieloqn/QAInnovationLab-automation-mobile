package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    private final AndroidDriver driver;

    // Selectors
    private static final String CART_ACCESS = "Cart"; // accessibility id
    private static final String CART_ICON_ID = "com.saucelabs.mydemoapp.android:id/cartIV";
    private static final String CART_ITEM_TITLE_ID = "com.saucelabs.mydemoapp.android:id/titleTV";

    public CartPage(AndroidDriver driver) { this.driver = driver; }

    public void openCart() {
        try {
            driver.findElement(AppiumBy.accessibilityId("Cart")).click();
        } catch (Exception e) {
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV")).click();
        }
    }

    public boolean hasItems() {
        return !driver.findElements(AppiumBy.id("com.saucelabs.mydemoapp.android:id/titleTV")).isEmpty();
    }

}
