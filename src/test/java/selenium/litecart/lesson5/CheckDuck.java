package selenium.litecart.lesson5;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;


public class CheckDuck  {
    private WebDriver driver;

    public boolean parseGray(String input) {
        Pattern c = Pattern.compile("rgba *\\( *([0-9]+), *\\1, *\\1, *[0-9] *\\)");
        Matcher m = c.matcher(input);

        return m.matches();
    }
    public boolean parseRed(String input) {
        Pattern c = Pattern.compile("rgba *\\( *([0-9]+), *0, *0, *[0-9] *\\)");
        Matcher m = c.matcher(input);

        return m.matches();
    }

    public void checkDuck(WebDriver driver){
        driver.get("http://localhost:8080/litecart/");

        List<WebElement> products = driver.findElements(By.cssSelector("#box-campaigns  li.product"));

        assertTrue(products.size() > 0);

        WebElement product = products.get(0);

        // Получаем название и цены с главной страницы
        String mainName = product.findElement(By.cssSelector("div.name")).getText();
        String mainRegularPrice = product.findElement(By.cssSelector(".regular-price")).getText();
        String mainCampaignPrice = product.findElement(By.cssSelector(".campaign-price")).getText();

        // Проверяем, что цвет обычной цены серый
        String colorRegular = product.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        assertTrue(parseGray(colorRegular));

        // Проверяем, что обычная цена зачеркнута
        WebElement p = product.findElement(By.cssSelector(".regular-price"));
        String regularTag = p.getTagName();
        assertEquals("s", regularTag);


        // Получаем размер шрифта обычной цены
        String sizeRegular = product.findElement(By.cssSelector(".regular-price"))
                .getCssValue("font-size");
        double sizeRegularDouble = Double.parseDouble(sizeRegular.substring(0, sizeRegular.length() - 2));
        System.out.println(sizeRegularDouble);

        // Получаем цвет для акционной цены, убежаемся, что она красная
        String colorCamp = product.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        assertTrue(parseRed(colorCamp));

        // Убеждаемся, что акционная цена жирная
        String textDecorationCamp = product.findElement(By.cssSelector(".campaign-price")).getTagName();
        assertEquals("STRONG", textDecorationCamp.toUpperCase());

        // Получаем размер шрифта акционной цены
        String sizeCamp = product.findElement(By.cssSelector(".campaign-price"))
                .getCssValue("font-size");
        double sizeCampDouble = Double.parseDouble(sizeCamp.substring(0, sizeCamp.length() - 2));
        System.out.println(sizeCampDouble);
        assertTrue(sizeCampDouble > sizeRegularDouble);


        // Переходим на страницу утки
        driver.findElement(By.cssSelector("#box-campaigns  li.product a")).click();
        // Получаем ее заголовок и цены
        String pageName = driver.findElement(By.cssSelector("h1")).getText();
        String pageRegularPrice = driver.findElement(By.cssSelector(".regular-price")).getText();
        String pageCampaignPrice = driver.findElement(By.cssSelector(".campaign-price")).getText();



        // Проверяем, что цвет обычной цены серый
        colorRegular = driver.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        assertTrue(parseGray(colorRegular));

        // Проверяем, что обычная цена зачеркнута
        regularTag = driver.findElement(By.cssSelector(".regular-price")).getTagName();
        assertEquals("s", regularTag);

        // Получаем размер шрифта обычной цены
        sizeRegular = driver.findElement(By.cssSelector(".regular-price"))
                .getCssValue("font-size");
        sizeRegularDouble = Double.parseDouble(sizeRegular.substring(0, sizeRegular.length() - 2));
        System.out.println(sizeRegularDouble);

        // Получаем цвет для акционной цены, убежаемся, что она красная
        colorCamp = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        assertTrue(parseRed(colorCamp));

        // Убеждаемся, что акционная цена жирная
        textDecorationCamp = driver.findElement(By.cssSelector(".campaign-price")).getTagName();
        assertEquals("STRONG", textDecorationCamp.toUpperCase());

        // Получаем размер шрифта акционной цены
        sizeCamp = driver.findElement(By.cssSelector(".campaign-price"))
                .getCssValue("font-size");
        sizeCampDouble = Double.parseDouble(sizeCamp.substring(0, sizeCamp.length() - 2));
        System.out.println(sizeCampDouble);
        assertTrue(sizeCampDouble > sizeRegularDouble);


        // Сравниваем заголовк и цены
        assertEquals(mainName, pageName);
        assertEquals(mainRegularPrice, pageRegularPrice);
        assertEquals(mainCampaignPrice, pageCampaignPrice);

    }

    @Test
    public void checkDuckChrome(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        checkDuck(driver);
        driver.quit();
    }

    @Test
    public void checkDuckIE(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        WebDriver driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        checkDuck(driver);
        driver.quit();
    }

    @Test
    public void checkDuckFirefox(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        checkDuck(driver);
        driver.quit();
    }
}
