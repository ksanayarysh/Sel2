package selenium.litecart.lesson6;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import selenium.litecart.BaseTest;

import java.util.Random;

public class Login extends BaseTest {

    @Test
    public void login(){
        Random r = new Random();
        int emailId = r.nextInt(10000);
        String email = emailId + "@mail.ru";
        String password = "123456";

        getDriver().get("http://localhost:8080/litecart/");
        // Переходим на страницу регистрации нового пользователя
        getDriver().findElement(By.cssSelector("form[name=login_form] a")).click();

        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=firstname]")).sendKeys("John");
        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=lastname]")).sendKeys("Dow");
        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=address1]")).sendKeys("Address1");
        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=postcode]")).sendKeys("12455");
        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=city]")).sendKeys("New York");

        Select selectCountry = new Select(getDriver().findElement(By.cssSelector("form[name=customer_form] [name=country_code]")));
        selectCountry.selectByValue("US");


        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=phone]")).sendKeys("+123456789");
        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=email]")).sendKeys(email);

        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=password]")).sendKeys(password);
        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=confirmed_password]")).sendKeys(password);

        getDriver().findElement(By.cssSelector("form[name=customer_form] button[name=create_account")).click();

        Select selectZone = new Select(getDriver().findElement(By.cssSelector("form[name=customer_form] select[name=zone_code]")));
        selectZone.selectByValue("AK");

        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=password]")).sendKeys(password);
        getDriver().findElement(By.cssSelector("form[name=customer_form] input[name=confirmed_password]")).sendKeys(password);
        getDriver().findElement(By.cssSelector("form[name=customer_form] button[name=create_account")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        getDriver().findElement(By.cssSelector("form[name=login_form] input[name=email]")).sendKeys(email);
        getDriver().findElement(By.cssSelector("form[name=login_form] input[name=password]")).sendKeys(password);
        getDriver().findElement(By.cssSelector("form[name=login_form] button[name=login]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

    }

}