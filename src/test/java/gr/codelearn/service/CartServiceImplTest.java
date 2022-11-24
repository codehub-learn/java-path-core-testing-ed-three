/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gr.codelearn.service;

import gr.codelearn.domain.Item;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author mcjohn1
 */
@ExtendWith(MockitoExtension.class)
@Tag("onMemory")
public class CartServiceImplTest {

    private CartService cartService;

    @Mock
    private PaymentService paymentService;

    @BeforeAll
    public static void beforeAll() {
        // class level setup
    }

    @BeforeEach
    public void beforeEach() {
        cartService = new CartServiceImpl(paymentService);
    }

    @Test
    public void addItemAndCheckIfCartContainsItem() throws Exception {
        Item item = new Item("Potatoes", BigDecimal.valueOf(3), 1);
        cartService.addItem(item);
        List<Item> retrievedItems = cartService.getCartItems();
        assertEquals(1, retrievedItems.size());
    }

    @Test
    public void addItemWithZeroQuantityAndCheckIfItemHasBeenAdded() throws Exception {
        Item item = new Item("Potatoes", BigDecimal.valueOf(3), 0);
        assertFalse(cartService.addItem(item));
    }

    @Test
    public void addNullItemAndCheckIfItemHasBeenAdded() throws Exception {
        Item item = null;
        assertThrows(Exception.class, () -> cartService.addItem(item));
    }

    @Test
    public void addAnItemTwiceAndCheckIfCartIsCorrectlyUpdated() throws Exception {
        Item potatoes1 = new Item("Potatoes", BigDecimal.valueOf(3), 1);
        cartService.addItem(potatoes1);
        Item potatoes2 = new Item("Potatoes", BigDecimal.valueOf(3), 4);
        cartService.addItem(potatoes2);
        List<Item> retrievedItems = cartService.getCartItems();
        Item retrievedPotatoes = retrievedItems.get(0);
        assertEquals(5, (int) retrievedPotatoes.getQuantity());
    }

    @Test
    public void addItemsAndCheckIfCartIsInInsertionOrder() throws Exception {
        Item item1 = new Item("Potatoes", BigDecimal.valueOf(3), 1);
        cartService.addItem(item1);
        Item item2 = new Item("Tomatoes", BigDecimal.valueOf(3), 4);
        cartService.addItem(item2);
        List<Item> retrievedItems = cartService.getCartItems();
        Assertions.assertIterableEquals(List.of(item1, item2), retrievedItems);
    }

    @Test
    public void checkoutWithoutError() throws Exception {
        Mockito.when(paymentService.getBalance()).thenReturn(BigDecimal.valueOf(550));
        Item item1 = new Item("Potatoes", BigDecimal.valueOf(3), 1);
        cartService.addItem(item1);
        Item item2 = new Item("Tomatoes", BigDecimal.valueOf(3), 4);
        cartService.addItem(item2);
        boolean result = cartService.checkout();
        Assertions.assertAll(
                () -> assertTrue(result),
                () -> assertEquals(0, cartService.getCartItems().size())
        );
    }
}
