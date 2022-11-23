/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.service;

import gr.codelearn.domain.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcjohn1
 */
public class CartServiceImpl implements CartService {

    private final List<Item> cart;

    public CartServiceImpl() {
        cart = new ArrayList<>();
    }

    @Override
    public boolean addItem(Item item) {
        if (item != null) {
            return cart.add(item);
        }
        return false;
    }

    @Override
    public boolean removeItem(Item item) {
        return cart.remove(item);
    }

    @Override
    public List<Item> getCartItems() {
        return cart;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return cart.stream().map(item -> item.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean checkout() {
        cart.clear();
        return true;
    }

}
