package edu.sm.product;

import edu.sm.service.ProductService;

import java.sql.SQLException;

public class ProductDelete {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();
            productService.deleteProduct(1);
            System.out.println("상품이 성공적으로 삭제되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}