@CartRegression
Feature: Validar carrito de compras en MyDemoApp

  Background:
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galeria

  Scenario Outline: Agregar productos y validar carrito
    When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Then valido el carrito de compra actualice correctamente

    Examples:
      | PRODUCTO                  | UNIDADES |
      | Sauce Labs Backpack       | 1        |
      | Sauce Labs Bolt T-Shirt   | 1        |
      | Sauce Labs Bike Light     | 2        |
