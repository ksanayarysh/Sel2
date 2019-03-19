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
        int loginName = r.nextInt(10000);

        getDriver().get("http://localhost/litecart/");
        // Переходим на страницу регистрации нового пользователя
        getDriver().findElement(By.cssSelector("[name=login_form] a")).click();

        getDriver().findElement(By.cssSelector("[name=customer_form] [name=firstname]")).sendKeys("John");
        getDriver().findElement(By.cssSelector("[name=customer_form] [name=lastname]")).sendKeys("Dow");
        getDriver().findElement(By.cssSelector("[name=customer_form] [name=address1]")).sendKeys("Address1");
        getDriver().findElement(By.cssSelector("[name=customer_form] [name=postcode]")).sendKeys("12455");
        getDriver().findElement(By.cssSelector("[name=customer_form] [name=city]")).sendKeys("New York");

        Select select = new Select(getDriver().findElement(By.cssSelector("[name=customer_form] [name=country_code]")));
        select.selectByValue("US");

        getDriver().findElement(By.cssSelector("[name=customer_form] [name=phone]")).sendKeys("123456789");
    }

}
