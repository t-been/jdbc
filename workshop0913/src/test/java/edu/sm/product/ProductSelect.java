package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductSelect {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();
            List<Product> products = productService.getAllProducts();

            // name, price, size, color 모두 출력하도록 수정
            for (Product product : products) {
                System.out.println(product.getName() + " - " + product.getPrice() + " - " +
                        product.getSize() + " - " + product.getColor());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}