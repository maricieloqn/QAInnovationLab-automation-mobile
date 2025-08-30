package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public ProductListPage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /** Hace scroll dentro del ScrollView y toca el título del producto. */
    public void openProduct(String exactTitle) {
        // Scroll dentro del scrollView principal hasta el TextView con id titleTV y ese texto
        String uiScrollable = "new UiScrollable(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/scrollView\"))"
                + ".scrollIntoView(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/titleTV\").text(\"" + exactTitle + "\"))";

        By byTitle = AppiumBy.androidUIAutomator(uiScrollable);

        WebElement titleEl = wait.until(ExpectedConditions.presenceOfElementLocated(byTitle));
        titleEl.click();
    }
}
