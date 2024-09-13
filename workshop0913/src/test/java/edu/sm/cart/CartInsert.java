package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

public class CartInsert {
    public static void main(String[] args) {

        CartService cartService = new CartService();
        Cart cart = Cart.builder()
                .cId(1)   // Customer ID
                .pId(1)   // Product ID
                .count(1) // 수량
                .build();

        try {
            cartService.add(cart);
        } catch (Exception e) {
            System.out.println("시스템 장애 발생");
            e.printStackTrace();
        }
    }
}