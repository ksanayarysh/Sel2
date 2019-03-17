package selenium.litecart.lesson5;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



public class CheckDuck extends BaseTest {

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

    @Test
    public void checkDuck(){
        getDriver().get("http:/localhost/litecart/");

        List<WebElement> products = getDriver().findElements(By.cssSelector("#box-campaigns  li.product"));

        assertTrue(products.size() > 0);

        WebElement product = products.get(0);

        // Получаем название и цены с главной страницы
        String mainName = product.findElement(By.cssSelector("div.name")).getAttribute("textContent");
        String mainRegularPrice = product.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String mainCampaignPrice = product.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");

        // Проверяем, что цвет обычной цены серый
        String colorRegular = product.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        assertTrue(parseGray(colorRegular));

        // Проверяем, что обычная цена зачеркнута
        String textDecorationRegular = product.findElement(By.cssSelector(".regular-price"))
                .getCssValue("text-decoration-line");
        assertEquals("line-through", textDecorationRegular);

        // Получаем размер шрифта обычной цены
        String sizeRegular = product.findElement(By.cssSelector(".regular-price"))
                .getCssValue("font-size");
        System.out.println(sizeRegular);

        // Получаем цвет для акционной цены, убежаемся, что она красная
        String colorCamp = product.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        assertTrue(parseRed(colorCamp));

        // Убеждаемся, что акционная цена жирная
        String textDecorationCamp = product.findElement(By.cssSelector(".campaign-price"))
                .getAttribute("tagName");
        assertEquals("STRONG", textDecorationCamp);

        // Получаем размер шрифта обычной цены
        String sizeCamp = product.findElement(By.cssSelector(".campaign-price"))
                .getCssValue("font-size");
        System.out.println(sizeCamp);


        // Переходим на страницу утки
        getDriver().findElement(By.cssSelector("#box-campaigns  li.product a")).click();
        // Получаем ее заголовок и цены
        String pageName = getDriver().findElement(By.cssSelector("h1")).getAttribute("textContent");
        String pageRegularPrice = getDriver().findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String pageCampaignPrice = getDriver().findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");

        // Сравниваем заголовк и цены
        assertEquals(mainName, pageName);
        assertEquals(mainRegularPrice, pageRegularPrice);
        assertEquals(mainCampaignPrice, pageCampaignPrice);

    }
}
