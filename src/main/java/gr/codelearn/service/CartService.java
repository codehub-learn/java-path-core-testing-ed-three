/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.service;

import gr.codelearn.domain.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mcjohn1
 */
public interface CartService {
    boolean addItem(Item item);
    boolean removeItem(Item item);
    List<Item> getCartItems();
    BigDecimal getTotalPrice();
    boolean checkout();
}
