/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gr.codelearn.service;

import gr.codelearn.domain.Item;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mcjohn1
 */
public class CartServiceImplTest {

    public CartServiceImplTest() {
    }

    @Test
    public void addItemAndCheckIfCartContainsItem() {
        Item item = new Item("Potatoes", BigDecimal.valueOf(3), 1);
        CartService cartService = new CartServiceImpl();
        cartService.addItem(item);
        List<Item> retrievedItems = cartService.getCartItems();
        assertEquals(1, retrievedItems.size());
    }

    @Test
    public void addNullItemAndCheckIfCartDoesNotContainItem() {
        Item item = null;
        CartService cartService = new CartServiceImpl();
        cartService.addItem(item);
        List<Item> retrievedItems = cartService.getCartItems();
        assertEquals(0, retrievedItems.size());
    }

}
