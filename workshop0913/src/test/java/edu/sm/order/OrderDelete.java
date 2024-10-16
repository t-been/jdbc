package edu.sm.order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

public class OrderDelete {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        int id = 6;
        Order order = Order.builder()
                .id(id)
                .build();
        try {
            orderService.remove(order.getId());
        } catch (Exception e) {
            System.out.println("시스템 장애 발생");
            e.printStackTrace();
        }
    }
}
