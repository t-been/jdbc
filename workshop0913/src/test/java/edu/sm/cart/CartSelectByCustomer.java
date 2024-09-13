package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

import java.util.List;

public class CartSelectByCustomer {

    public static void main(String[] args) {
        CartService cartService = new CartService();
        int customerId = 2;  // 조회할 사용자 ID

        try {
            List<Cart> carts = cartService.getCartByCustomerId(customerId);
            System.out.println("사용자 " + customerId + "의 장바구니: " + carts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}