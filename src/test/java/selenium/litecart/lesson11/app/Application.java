package selenium.litecart.lesson11.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.litecart.lesson11.pages.CartPage;
import selenium.litecart.lesson11.pages.MainPage;
import selenium.litecart.lesson11.pages.ProductPage;

public class Application {
    public WebDriver driver;

    private MainPage mainPage;
    private CartPage cartPage;
    private ProductPage productPage;

    public Application(){
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public void addProductToCart(int count){
        if (mainPage.open().isNotLogged()){
            mainPage.enterEmail("ks@mail.com").enterPassword("11111").submitLogin();
        }

        for (int i = 0; i < count; i++) {
            productPage.open(mainPage.open().getFirstProduct()).addProductToCart();
        }

    }

    public int getQuantityInCart(){
        return mainPage.open().getQuantityInCart();
    }

    public void deleteProductFromCart(){
        cartPage.open().deleteProductFromCart();
    }
}
