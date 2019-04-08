package selenium.litecart.lesson11.tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CartTest extends TestBase{

    @Test
    public void addAndDeleteProduct(){
        int initial_count = app.getQuantityInCart();
        app.addProductToCart(3);
        assertEquals(initial_count + 3, app.getQuantityInCart());
        app.deleteProductFromCart();
        assertEquals(0, app.getQuantityInCart());
    }
}
