
package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.CartPage;
import com.qa.pages.InventoryPage;
import com.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class SauceDemoTests extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(),
                "Inventory page should be displayed after login");
    }

    @Test
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "wrong_password");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password"),
                "Error message should be displayed for invalid login");
    }

    @Test
    public void addProductToCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItems().size(), 1,
                "One product should be present in the cart");
    }

    @Test
    public void checkoutNavigationTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),
                "Checkout information page should open");
    }
    @Test
public void completeCheckoutInformationTest() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("standard_user", "secret_sauce");

    InventoryPage inventoryPage = new InventoryPage(driver);
    inventoryPage.addProductToCart("Sauce Labs Backpack");
    inventoryPage.openCart();

    CartPage cartPage = new CartPage(driver);
    cartPage.clickCheckout();

    driver.findElement(By.id("first-name")).sendKeys("Gaurav");
    driver.findElement(By.id("last-name")).sendKeys("Srivastava");
    driver.findElement(By.id("postal-code")).sendKeys("201301");

    driver.findElement(By.id("continue")).click();

    Assert.assertTrue(
        driver.getCurrentUrl().contains("checkout-step-two"),
        "Checkout overview page should open"
    );
}
        @Test
    public void removeProductFromCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem("Sauce Labs Backpack");

        Assert.assertEquals(cartPage.getCartItems().size(), 0,
                "Cart should be empty after removing the product");
    }
}
