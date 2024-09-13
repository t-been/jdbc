package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

import java.util.List;

// 장바구니 내의 모든 상품 조회
public class CartSelect {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        List<Cart> carts = null;
        try {
            carts = cartService.get();  // 모든 장바구니 아이템 조회
            System.out.println(carts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}