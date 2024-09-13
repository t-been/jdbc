package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.sql.SQLException;

public class ProductSelectOne {
    public static void main(String[] args) {
        try {
            // 특정 ID로 조회할 상품 ID 설정
            int productId = 4;

            ProductService productService = new ProductService();
            Product product = productService.getProductById(productId);

            if (product != null) {
                // 조회된 상품 정보 출력
                System.out.println("ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Size: " + product.getSize());
                System.out.println("Color: " + product.getColor());
                System.out.println("Registration Date: " + product.getRegistrationDate());
            } else {
                System.out.println("해당 ID의 상품을 찾을 수 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}