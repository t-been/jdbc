package edu.sm.service;

import edu.sm.dao.ProductDao;
import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedEmailException;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    // 생성자에서 throws SQLException 추가
    public ProductService() throws SQLException {
        this.productDao = new ProductDao();
    }

    // 상품 추가
    public void addProduct(Product product) throws SQLException, DuplicatedEmailException {
        productDao.addProduct(product);
    }

    // 모든 상품 조회
    public List<Product> getAllProducts() throws SQLException {
        return productDao.getAllProducts();
    }

    // 특정 상품 조회
    public Product getProductById(int id) throws SQLException {
        return productDao.getProductById(id);
    }

    // 상품 수정
    public void updateProduct(Product product) throws SQLException {
        productDao.updateProduct(product);
    }

    // 상품 삭제
    public void deleteProduct(int id) throws SQLException {
        productDao.deleteProduct(id);
    }
}