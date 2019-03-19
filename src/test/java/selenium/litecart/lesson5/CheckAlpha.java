package selenium.litecart.lesson5;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.litecart.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CheckAlpha extends BaseTest {

    // Создадим ArrayList на основе названий стран и отсортируем его, далее посмотрим, совпадает ли отсортированный массив с исходным
    private void checkSort(ArrayList<String> array) {
        ArrayList<String> tmp = new ArrayList<>(array);
        Collections.sort(tmp);
        assertEquals(tmp, array);
    }



    // Тест для проверки сортировки стран и геозон
    //    а странице http://localhost/litecart/admin/?app=countries&doc=countries
    //    а) проверить, что страны расположены в алфавитном порядке
    //    б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и
    //    там проверить, что зоны расположены в алфавитном порядке
    @Test
    public void checkCountry() {
        getDriver().get("http:/localhost/litecart/admin/");

        getDriver().findElement(By.xpath("//*[@name=\"username\"]")).sendKeys("admin");
        getDriver().findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("123456");
        getDriver().findElement(By.xpath("//*[@name=\"login\"]")).click();

        getDriver().get("http://localhost/litecart/admin/?app=countries&doc=countries");

        ArrayList<String> countries = new ArrayList<>();

        // Найдем номера столбцов, где хранятся страны и зоны
        int countryCol = -1;
        int zoneCol = -1;

        List<WebElement> header = getDriver().findElements(By.cssSelector("table.dataTable tr.header th"));
        for (int i = 0; i < header.size(); i++) {
            if (header.get(i).getText().equals("Name")) countryCol = i;
            if (header.get(i).getText().equals("Zones")) zoneCol = i;
        }

        // Проверим, что колонка нашлась

        assertTrue("Не найдена колонка Name", countryCol != -1);
        assertTrue("Не найдена колонка Zones", zoneCol != -1);

        //"table.dataTable tr.row"

        List<WebElement> allRows = getDriver().findElements(By.cssSelector("table.dataTable tr.row"));
        for (int i = 0; i < allRows.size(); i++) {
            WebElement row = allRows.get(i);

            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            countries.add(cells.get(countryCol).getText());

            int zone = Integer.parseInt(cells.get(zoneCol).getText());
            if (zone > 0) {
                WebElement country = row.findElement(By.cssSelector("a"));
                country.click();

                List<WebElement> countryZones = getDriver().findElements(By.cssSelector("table.dataTable input[name*=zones][name*=name]"));
                ArrayList<String> countryZonesNames = new ArrayList<>();
                for (WebElement z : countryZones) countryZonesNames.add(z.getAttribute("value"));

                checkSort(countryZonesNames);

                getDriver().get("http://localhost/litecart/admin/?app=countries&doc=countries");
                allRows = getDriver().findElements(By.cssSelector("table.dataTable tr.row"));

            }
        }

        checkSort(countries);
    }

    // Тест для проверки геозон
    //    2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
    //    зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
    @Test
    public void checkZone() {
        getDriver().get("http:/localhost/litecart/admin/");

        getDriver().findElement(By.xpath("//*[@name=\"username\"]")).sendKeys("admin");
        getDriver().findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("123456");
        getDriver().findElement(By.xpath("//*[@name=\"login\"]")).click();

        getDriver().get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        int nameCol = -1;
        List<WebElement> header = getDriver().findElements(By.cssSelector("table.dataTable tr.header th"));

        for (int i = 0; i < header.size(); i++)
            if (header.get(i).getText().equals("Name")) nameCol = i;

        assertTrue("Не найден столбец названия", nameCol != -1);

        List<WebElement> tableRows = getDriver().findElements(By.cssSelector("form[name=geo_zones_form] table tr.row"));

        for (int i = 0; i < tableRows.size(); i++) {
            WebElement tr = tableRows.get(i);

            WebElement link = tr.findElement(By.cssSelector("td a"));
            link.click();

            List<WebElement> zones = getDriver().findElements(By.cssSelector("select[name*=zone_code]"));
            ArrayList<String> zonesString = new ArrayList<>();
            for (WebElement z : zones) {
                List<WebElement> options = z.findElements(By.cssSelector("option"));
                int selectedIndex = Integer.parseInt(z.getAttribute("selectedIndex"));
                String selectedOption = options.get(selectedIndex).getText();
                zonesString.add(selectedOption);
            }

            checkSort(zonesString);

            getDriver().get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

            tableRows = getDriver().findElements(By.cssSelector("form[name=geo_zones_form] table tr.row"));

        }


    }

}

