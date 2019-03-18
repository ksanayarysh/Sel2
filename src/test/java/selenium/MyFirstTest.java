package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.edge.driver", "c:\\tools\\MicrosoftWebDriver.exe");
        System.setProperty("webdriver.chrome.driver", "c:\\tools\\chromedriver.exe");
        System.setProperty("webdriver.geckodriver.driver", "c:\\tools\\geckodriver.exe");
        System.setProperty("webdriver.ie.driver", "c:\\tools\\IEDriverServer.exe");
        //driver = new ChromeDriver();
       // driver = new InternetExplorerDriver();
        driver = new EdgeDriver();
       // driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void myFirstTest() {
        driver.get("https://www.google.com/");

        driver.findElement(By.xpath("//*[@name='q']")).sendKeys("webdriver");
        //driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.xpath("//*[@name='btnK']")).submit();
//        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
