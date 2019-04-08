package selenium.litecart.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage open(String productPage){
        driver.get(productPage);
        return this;
    }

    private boolean isSelectPresent(WebDriver driver) {
        return driver.findElements(By.cssSelector("select[name^=options]")).size() > 0;
    }

    public void addProductToCart(){

        if (isSelectPresent(driver)) {
            Select size = new Select(driver.findElement(By.cssSelector("select[name^=options]")));
            size.selectByValue("Small");
        }

        int count = getQuantity();
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("#cart span[class=quantity]"), "textContent", "" + (count + 1)));
    }

    public int getQuantity(){
        return Integer.parseInt(driver.findElement(By.cssSelector("#cart span[class=quantity]")).getAttribute("textContent"));
    }

}
