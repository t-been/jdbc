package edu.sm.cart;

import edu.sm.service.CartService;

// 장바구니에서 특정 상품 제거
public class CartDelete {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        int cartId = 1;  // 삭제할 Cart ID

        try {
            cartService.remove(cartId);
            System.out.println("장바구니에서 상품 삭제 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}