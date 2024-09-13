package edu.sm.dao;

import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedEmailException;
import edu.sm.frame.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection connection;

    // 생성자
    public ProductDao() throws SQLException {
        ConnectionPool pool = ConnectionPool.create();
        connection = pool.getConnection();
    }

    // 상품 추가
    public void addProduct(Product product) throws SQLException, DuplicatedEmailException {
        String sql = "INSERT INTO Product (id, name, price, size, color, registration_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, product.getId());  // 중복된 ID 확인
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getPrice());
            pstmt.setString(4, product.getSize());
            pstmt.setString(5, product.getColor());
            pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // 중복된 ID로 인해 SQLIntegrityConstraintViolationException 발생 시 처리
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new DuplicatedEmailException("중복된 ID가 존재합니다: " + product.getId());
            } else {
                throw e;
            }
        }
    }

    // 모든 상품 조회
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM Product";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("size"),
                        rs.getString("color"),
                        rs.getTimestamp("registration_date")
                );
                products.add(product);
            }
        }
        return products;
    }

    // 특정 상품 조회
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE id = ?";
        Product product = null;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("size"),
                        rs.getString("color"),
                        rs.getTimestamp("registration_date")
                );
            }
        }
        return product;
    }

    // 상품 수정
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET name = ?, price = ?, size = ?, color = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setString(3, product.getSize());
            pstmt.setString(4, product.getColor());
            pstmt.setInt(5, product.getId());
            pstmt.executeUpdate();
        }
    }

    // 상품 삭제
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}