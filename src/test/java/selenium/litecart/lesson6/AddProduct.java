package selenium.litecart.lesson6;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.litecart.BaseTest;

import java.io.File;
import java.util.List;

import static java.lang.Thread.sleep;

public class AddProduct extends BaseTest {

    public String getAbsolutePath(){
        File file = new File("img/duck.jpg");
        return file.getAbsolutePath();
    }

    public void fillPrice() {
        getDriver().findElement(By.cssSelector("input[name=purchase_price]")).clear();
        getDriver().findElement(By.cssSelector("input[name=purchase_price]")).sendKeys("20");
        Select selectCurrencyCode = new Select(getDriver().findElement(By.cssSelector("select[name=purchase_price_currency_code]")));
        selectCurrencyCode.selectByValue("USD");

        getDriver().findElement(By.cssSelector("table input[name=\"prices[USD]\"]")).sendKeys("20");
        getDriver().findElement(By.cssSelector("table input[name=\"prices[EUR]\"]")).sendKeys("18");
    }

    public void fillInformation(){
        Select selectManufacturer = new Select(getDriver().findElement(By.cssSelector("select[name=manufacturer_id]")));
        selectManufacturer.selectByValue("1");

        getDriver().findElement(By.cssSelector("input[name=keywords]")).sendKeys("Duck alive");
        getDriver().findElement(By.cssSelector("input[name*=short_description]")).sendKeys("Nice village duck");
        getDriver().findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Your children will be happy");
        getDriver().findElement(By.cssSelector("input[name*=head_title]")).sendKeys("Duck... just duck");
        getDriver().findElement(By.cssSelector("input[name*=meta_description]")).sendKeys("Nice yellow duck");
    }

    public void fillGeneral(){
        List<WebElement> status = getDriver().findElements(By.cssSelector("[name=status]"));
        if (status.get(0).getAttribute("value").equals("1")) status.get(0).click();
        else status.get(1).click();

        getDriver().findElement(By.cssSelector("[name*=name]")).sendKeys("Our new super duck!");
        getDriver().findElement(By.cssSelector("[name=code]")).sendKeys("0101010");

        getDriver().findElement(By.cssSelector("input[data-name=\"Rubber Ducks\"]")).click();
        Select selectCountry = new Select(getDriver().findElement(By.cssSelector("select[name=default_category_id]")));
        selectCountry.selectByValue("1");

        List<WebElement> groups =  getDriver().findElements(By.cssSelector("input[name*=product_groups]"));
        for(WebElement we : groups)
            if(we.getAttribute("value").equals("1-3")) we.click();

        getDriver().findElement(By.cssSelector("input[name=quantity]")).sendKeys("250");
        getDriver().findElement(By.cssSelector("input[type=file]")).sendKeys(getAbsolutePath());

        getDriver().findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys("20.03.2019");
        getDriver().findElement(By.cssSelector("input[name=date_valid_to]")).sendKeys("20.03.2020");
    }

    @Test
    public void addProduct() throws InterruptedException {
        getDriver().get("http:/localhost/litecart/admin/");

        getDriver().findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        getDriver().findElement(By.cssSelector("[name=password")).sendKeys("123456");
        getDriver().findElement(By.cssSelector("[name=login]")).click();

        getDriver().findElement(By.xpath("//span[text()='Catalog']")).click();
        getDriver().findElement(By.xpath("//a[text()=\" Add New Product\"]")).click();

        List<WebElement> tabs = getDriver().findElements(By.cssSelector("div.tabs ul.index a"));
        for (WebElement tab : tabs) {
            if (tab.getText().equals("General")) fillGeneral();
            if (tab.getText().equals("Information")) {
                tab.click();
                sleep(1000);
                fillInformation();
            }
            if (tab.getText().equals("Prices")) {
                tab.click();
                sleep(1000);
                fillPrice();
            }
        }

        getDriver().findElement(By.cssSelector("button[name=save]")).click();
    }

}