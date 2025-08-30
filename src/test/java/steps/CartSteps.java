package steps;

import base.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import pages.CartPage;
import pages.ProductDetailPage;
import pages.ProductListPage;

public class CartSteps {
    private final AndroidDriver driver = DriverManager.getDriver();
    private final ProductListPage productListPage = new ProductListPage(driver);
    private final ProductDetailPage productDetailPage = new ProductDetailPage(driver);
    private final CartPage cartPage = new CartPage(driver);  // <--- AHORA SE USA

    public void openAppAndValidateGallery() {
        productListPage.waitForGalleryLoaded();
    }

    public void addUnitsOfProduct(int units, String productName) {
        productListPage.openProduct(productName);
        productDetailPage.setUnits(units);
        productDetailPage.addToCart();
    }

    public void validateCartUpdatedCorrectly() {
        cartPage.openCart();
        if (!cartPage.hasItems()) {
            throw new AssertionError("El carrito debería tener items");
        }
    }
}
