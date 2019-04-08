package selenium.litecart.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open(){
        driver.get("http://localhost/litecart/en/checkout");
        return this;
    }

    public void deleteProductFromCart(){
        int count = driver.findElements(By.cssSelector("#box-checkout-summary td[class=item]")).size();

        while (count > 0) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name=remove_cart_item]"))).click();
            count -= 1;
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#box-checkout-summary td[class=item]"), count));
            count = driver.findElements(By.cssSelector("#box-checkout-summary td[class=item]")).size();
        }

    }

}
