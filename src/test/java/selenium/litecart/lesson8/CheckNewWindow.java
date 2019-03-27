package selenium.litecart.lesson8;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import selenium.litecart.BaseTest;

import java.util.List;
import java.util.Set;

public class CheckNewWindow extends BaseTest {

    private void adminLogin() {
        getDriver().get("http:/localhost/litecart/admin/");

        getDriver().findElement(By.xpath("//*[@name=\"username\"]")).sendKeys("admin");
        getDriver().findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("123456");
        getDriver().findElement(By.xpath("//*[@name=\"login\"]")).click();
    }

    private ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @Test
    public void checkNewWindow() {
        adminLogin();

        getDriver().get("http://localhost/litecart/admin/?app=countries&doc=countries");

        // Переходим на редактирование
        getDriver().findElement(By.xpath("//*[contains(@class, 'fa-pencil')]//ancestor::a")).click();

        // Получаем handle основного окна
        String mainWindow = getDriver().getWindowHandle();
        // И set of handles

        Set<String> oldWindows = getDriver().getWindowHandles();

        // Получаем список элементов, которые должны открываться в стороннем окне
        List<WebElement> links = getDriver().findElements(By.xpath("//*[contains(@class, 'fa-external-link')]//ancestor::a"));

        // Открыть окно || Необходимо вынести в цикл потом
        links.get(0).click();

        String newWindow = getWait().until(anyWindowOtherThan(oldWindows));
        assert(newWindow != null);
        getDriver().switchTo().window(newWindow);
        // Do something
        getDriver().close();
        getDriver().switchTo().window(mainWindow);
    }

}
