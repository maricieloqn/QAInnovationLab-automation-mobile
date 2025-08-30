package steps;

import base.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.appium.java_client.AppiumBy;
import pages.ProductListPage;
import pages.ProductDetailPage;

public class CartSteps {

    private final AndroidDriver driver = DriverManager.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    private final ProductListPage listPage = new ProductListPage(driver, wait);
    private final ProductDetailPage detailPage = new ProductDetailPage(driver, wait);

    private String normalize(String nameFromFeature) {
        // La app usa "Sauce Labs Bolt T-Shirt" (sin “ - ”). Normalizamos por si el feature trae guiones.
        return nameFromFeature.replace(" - ", " ")
                .replace("-", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public void openAppAndValidateGallery() {
        // Si fuera necesario, aquí podrías validar que exista el RecyclerView de productos
        // o tocar "Back" para asegurarte de estar en Home.
    }

    public void addUnitsOfProduct(Integer units, String productName) {
        String exact = normalize(productName);

        listPage.openProduct(exact);          // toca el producto en el listado
        detailPage.waitForPage(exact);        // espera el detalle cargado
        detailPage.setUnits(units);           // pone la cantidad solicitada
        detailPage.addToCart();               // agrega al carrito
    }

    // Ya te lo dejé antes, pero lo incluyo por claridad:
    public void validateCartHasItems() { validateCartUpdatedCorrectly(); }

    public void validateCartUpdatedCorrectly() {
        // Abre el carrito y verifica items
        try {
            driver.findElement(AppiumBy.accessibilityId("Cart")).click();
        } catch (Exception e) {
            driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV")).click();
        }

        // Validar que exista al menos 1 producto en el carrito
        boolean hasItem = !driver.findElements(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/titleTV")
        ).isEmpty();

        if (!hasItem) {
            throw new AssertionError("El carrito debería tener items");
        }
    }

}
