package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedEmailException;
import edu.sm.service.ProductService;

import java.sql.SQLException;
import java.util.Date;

public class ProductInsert {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();

            // 5개의 상품을 추가, 중복된 id 확인
            Product[] products = {
                    new Product(1, "Laptop", 1500, "15 inch", "Silver", new Date()),
                    new Product(2, "Smartphone", 800, "6 inch", "Black", new Date()),
                    new Product(3, "Tablet", 600, "10 inch", "White", new Date()),
                    new Product(4, "Monitor", 300, "24 inch", "Black", new Date()),
                    new Product(5, "Keyboard", 50, "Full Size", "White", new Date())  // 중복된 id
            };

            for (Product product : products) {
                try {
                    productService.addProduct(product);
                    System.out.println(product.getName() + "이(가) 성공적으로 추가되었습니다.");
                } catch (DuplicatedEmailException e) {
                    // 중복된 ID가 있을 때 예외 메시지 출력
                    System.out.println("ID가 중복 되어 입력이 안됩니다: " + product.getId());
                } catch (Exception e) {
                    // 기타 예외 처리
                    System.out.println("시스템 장애가 발생했습니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}