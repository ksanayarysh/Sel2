package selenium.litecart.lesson10;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import selenium.litecart.BaseTest;

import java.util.List;

public class Log extends BaseTest {
    private void adminLogin() {
        getDriver().get("http:/localhost:8080/litecart/admin/");

        getDriver().findElement(By.xpath("//*[@name=\"username\"]")).sendKeys("admin");
        getDriver().findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("123456");
        getDriver().findElement(By.xpath("//*[@name=\"login\"]")).click();
    }

    @Test
    public void checkLog() {
        adminLogin();
        getDriver().get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> products = getDriver().findElements(By.xpath("//table[@class='dataTable']//img/following-sibling::a"));

        for (int i = 0; i < products.size(); i++) {
            WebElement p = products.get(i);
            p.click();

            assert getDriver().manage().logs().get("browser").getAll().size() == 0;

            getDriver().get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            products = getDriver().findElements(By.xpath("//table[@class='dataTable']//img/following-sibling::a"));
        }

    }

}
