/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codelearn.service;

import java.math.BigDecimal;

/**
 *
 * @author mcjohn1
 */
public interface PaymentService {
    BigDecimal getBalance();
    boolean withdraw(BigDecimal amount);
}
