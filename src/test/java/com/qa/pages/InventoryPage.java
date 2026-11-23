
package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {

    private WebDriver driver;

    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By sortDropdown = By.className("product_sort_container");
    private By cartIcon = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(productNames);
    }

    public List<WebElement> getProductPrices() {
        return driver.findElements(productPrices);
    }

  public void sortProducts(String option) {
    Select dropdown = new Select(driver.findElement(sortDropdown));
    dropdown.selectByValue(option);
    }

 public void addProductToCart(String product) {
    String buttonId = "add-to-cart-" +
            product.toLowerCase().replace(" ", "-");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement button = wait.until(
            ExpectedConditions.elementToBeClickable(By.id(buttonId))
    );

    button.click();

    wait.until(ExpectedConditions.textToBe(
            By.id(buttonId.replace("add-to-cart-", "remove-")),
            "Remove"
    ));
}
    

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public boolean isInventoryPageDisplayed() {
        return driver.findElement(By.className("inventory_list")).isDisplayed();
    }
}
