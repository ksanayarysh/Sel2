package selenium.litecart.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get("http://localhost/litecart");
        return this;
    }

    public boolean isNotLogged() {
        return driver.findElements(By.cssSelector("[name=login_form]")).size() > 0;
    }

    public MainPage enterEmail(String email) {
        driver.findElement(By.name("email")).sendKeys(email);
        return this;
    }

    public MainPage enterPassword(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public void submitLogin() {
        driver.findElement(By.name("login")).click();
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("[class ^= notice ]")));
    }

    public String getFirstProduct() {
        return driver.findElement(By.cssSelector("li.product a")).getAttribute("href");
    }

    public int getQuantityInCart() {
        return Integer.parseInt(driver.findElement(By.cssSelector("#cart span[class=quantity]")).getAttribute("textContent"));
    }

}
