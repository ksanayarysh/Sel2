package selenium.litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class lesson4 {
    private WebDriver driver;
    private WebDriverWait wait;

    public boolean areElementsPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkLeftPanel(){
        driver.get("http:/localhost:8080/litecart/admin/");

        driver.findElement(By.xpath("//*[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@name=\"login\"]")).click();

        List<WebElement> leftPanel = driver.findElements(By.cssSelector("td#sidebar li"));
//        List<WebElement> leftPanel = driver.findElements(By.xpath("//*[@id=\"sidebar\"]//li");

        int count = leftPanel.size();

        for (int i = 0; i < count; i++){
            WebElement we = leftPanel.get(i);

            we.click();

            List<WebElement> level2 = driver.findElements(By.cssSelector("ul#box-apps-menu li > ul > li"));
            for (int j = 0; j < level2.size(); j++){

                level2.get(j).click();

                assertTrue(areElementsPresent(By.cssSelector(("h1"))));

                level2 = driver.findElements(By.cssSelector("ul#box-apps-menu li > ul > li"));
            }

            driver.get("http:/localhost:8080/litecart/admin/");
            leftPanel = driver.findElements(By.cssSelector("td#sidebar li"));
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
