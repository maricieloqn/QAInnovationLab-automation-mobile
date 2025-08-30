package stepsdefinitions;

import base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import steps.CartSteps;

public class CartStepDef {

    private final CartSteps cartSteps = new CartSteps();

    @Before
    public void beforeScenario() {
        // Si necesitas limpiar algo antes de cada escenario, hazlo aquí.
    }

    @After
    public void afterScenario() {
        // Cierra la sesión de Appium al finalizar cada escenario
        DriverManager.quit();
    }

    @Given("estoy en la aplicación de SauceLabs")
    public void estoy_en_la_aplicacion_de_sauce_labs() {

        cartSteps.openAppAndValidateGallery();
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void valido_que_carguen_correctamente_los_productos_en_la_galeria() {
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agrego_del_siguiente_producto(Integer unidades, String producto) {
        cartSteps.addUnitsOfProduct(unidades, producto);
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void valido_el_carrito_de_compra_actualice_correctamente() {
        cartSteps.validateCartUpdatedCorrectly();
    }
}
