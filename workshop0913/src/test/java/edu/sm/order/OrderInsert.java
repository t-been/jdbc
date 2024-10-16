package edu.sm.order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

import java.time.LocalDateTime;

public class OrderInsert {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        // 필드명에 맞게 빌더 패턴 수정
        Order order = Order.builder()
                .cart_Id(6)  // cart_Id -> cartId
                .order_Date(LocalDateTime.parse("2021-09-13T00:00:00"))  // order_Date -> orderDate
                .build();

        try {
            orderService.add(order);
        } catch (Exception e) {
            System.out.println("시스템 장애 발생");
            e.printStackTrace();
        }
    }
}
