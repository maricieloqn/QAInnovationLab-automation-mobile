package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By title     = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    private final By qtyLabel  = AppiumBy.id("com.saucelabs.mydemoapp.android:id/noTV");
    private final By plusBtn   = AppiumBy.id("com.saucelabs.mydemoapp.android:id/plusIV");
    private final By addToCart = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt");

    public ProductDetailPage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /** Espera a que cargue el detalle y (opcional) valida el título. */
    public void waitForPage(String expectedTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        // Si quieres ser más estricto:
        // String shown = driver.findElement(title).getText().trim();
        // if (!shown.equals(expectedTitle)) throw new AssertionError("Detalle incorrecto: "+shown);
    }

    public void setUnits(int units) {
        // cantidad actual
        wait.until(ExpectedConditions.visibilityOfElementLocated(qtyLabel));
        int current = Integer.parseInt(driver.findElement(qtyLabel).getText().trim());

        while (current < units) {
            wait.until(ExpectedConditions.elementToBeClickable(plusBtn)).click();
            // re-lee luego de pulsar
            current = Integer.parseInt(driver.findElement(qtyLabel).getText().trim());
        }
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
    }
}
