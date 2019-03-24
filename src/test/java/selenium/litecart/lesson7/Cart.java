package selenium.litecart.lesson7;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenium.litecart.BaseTest;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Cart extends BaseTest {

    // Есть ли такой элемент
    private boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    // логин
    private void login(String name, String password) {
        getDriver().findElement(By.cssSelector("form[name=login_form] input[name=email]")).sendKeys(name);
        getDriver().findElement(By.cssSelector("form[name=login_form] input[name=password]")).sendKeys(password);
        getDriver().findElement(By.cssSelector("form[name=login_form] button[name=login]")).click();
    }

    // Добавляем продукт в корзину
    private void addProductToCart() {
        getDriver().findElement(By.cssSelector("li.product a")).click();

        if (isElementPresent(getDriver(), By.cssSelector("select[name^=options]"))) {
            Select size = new Select(getDriver().findElement(By.cssSelector("select[name^=options]")));
            size.selectByValue("Small");
        }

        WebElement quantity = getDriver().findElement(By.cssSelector("#cart span[class=quantity]"));
        int qInt = Integer.parseInt(quantity.getAttribute("textContent"));

        getDriver().findElement(By.cssSelector("button[name=add_cart_product]")).click();

        getWait().until(ExpectedConditions.attributeToBe(By.cssSelector("#cart span[class=quantity]"), "textContent", "" + (qInt + 1)));
    }

    // Удаляем продукт из корзины
    public void removeProductFromCart() {
        int count = getDriver().findElements(By.cssSelector("#box-checkout-summary td[class=item]")).size();

        while (count > 0) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name=remove_cart_item]"))).click();
            count -= 1;
            getWait().until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#box-checkout-summary td[class=item]"), count));
            count = getDriver().findElements(By.cssSelector("#box-checkout-summary td[class=item]")).size();
        }
    }

    @Test
    public void cartAddAndRemove() {
        getDriver().get("http://localhost/litecart/en/");
        login("ks@mail.com", "11111");

        getWait().until(presenceOfElementLocated(By.cssSelector("div[class=\"notice success\"]")));

        for (int i = 0; i < 3; i++) {
            addProductToCart();
        }

        getDriver().findElement(By.cssSelector("#cart a[class=link]")).click();

        removeProductFromCart();
    }



}
