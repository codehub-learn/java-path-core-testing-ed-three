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
    private final PaymentService paymentService;

    public CartServiceImpl(PaymentService paymentService) {
        cart = new ArrayList<>();
        this.paymentService = paymentService;
    }

    @Override
    public boolean addItem(Item item) throws Exception {
        if (item == null) {
            throw new Exception("Item added was incorrect.");
        } else {
            if (item.getQuantity() > 0) {
                return addOrIncreaseQuantity(item);
            } else {
                return false;
            }
        }
    }

    private boolean addOrIncreaseQuantity(Item itemToBeAdded) {
        for (Item item : cart) {
            if (item.equals(itemToBeAdded)) {
                item.setQuantity(item.getQuantity() + itemToBeAdded.getQuantity());
                return true;
            }
        }
        return cart.add(itemToBeAdded);
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
        BigDecimal totalPrice = getTotalPrice();
        if (paymentService.getBalance().doubleValue() >= totalPrice.doubleValue()) {
            cart.clear();
            paymentService.withdraw(totalPrice);
            return true;
        }
        return false;
    }

}
