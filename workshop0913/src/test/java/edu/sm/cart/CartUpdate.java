package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

// 장바구니 내의 상품 수량 수정 : PK인 id는 장바구니 내의 상품의 id임
public class CartUpdate {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        int cartId = 1;  // 수정할 Cart ID
        Cart cart = Cart.builder()
                .id(cartId)
                .count(3) // 수량 변경
                .build();

        try {
            cartService.modify(cart);
            System.out.println("장바구니 상품 수량 수정 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}