package com.qa.pages;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {

    private WebDriver driver;

    private By cartItems = By.className("cart_item");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    private By itemNames = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

    public List<WebElement> getItemNames() {
        return driver.findElements(itemNames);
    }

    public void removeItem(String product) {
        String buttonId = "remove-" +
                product.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(buttonId)).click();
    }

   public void clickCheckout() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void continueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public boolean isCartPageDisplayed() {
        return driver.findElement(By.className("cart_list")).isDisplayed();
    }
}
