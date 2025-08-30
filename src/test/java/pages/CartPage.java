package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCartFromToolbar() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.accessibilityId("Cart"))).click();
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"cart\")")
            )).click();
        }
    }

    public boolean productIsVisible(String productName) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollTextIntoView(\"" + productName + "\")"
        ));
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + productName + "\")")).isDisplayed();
    }

    public double getCartTotal() {
        WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)total.*\\$ ?[0-9]+\\.[0-9]{2}\")")
        ));
        String amount = total.getText().replaceAll("(?i)total[: ]", "")
                .replace("$","").trim();
        return Double.parseDouble(amount);
    }
}
