package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.sql.SQLException;

public class ProductUpdate {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();
            Product product = productService.getProductById(1); // 1번 상품 가져오기
            if (product != null) {
                product.setName("Updated Laptop");
                product.setPrice(9000);
                productService.updateProduct(product);
                System.out.println("상품이 성공적으로 수정되었습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}