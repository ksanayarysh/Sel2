package selenium.litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class checkSticker {
    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isOnlyOne(By locator) {
        return driver.findElements(locator).size() == 1;
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkStick() {
        driver.get("http://localhost/litecart/");

        List<WebElement> list = driver.findElements(By.cssSelector("ul.listing-wrapper > li"));


            for (int i = 0; i < list.size(); i++) {
                List<WebElement> stick = list.get((i)).findElements(By.cssSelector("div.sticker"));
                assertTrue(stick.size() == 1);

        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

