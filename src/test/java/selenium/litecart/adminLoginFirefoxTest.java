package selenium.litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class adminLoginFirefoxTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "d:\\tools\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void login(){
        driver.get("http:/localhost:8080/litecart/admin/");

        driver.findElement(By.xpath("//*[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@name=\"login\"]")).click();

        wait.until(titleIs("My Store"));

        driver.findElement(By.xpath("//span[text()='Reports']")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
