package workshop;

import java.sql.*;

public class Search {
    public static void main(String[] args) {
        // 1. MySQL JDBC Driver를 로딩한다.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
            return;
        }

        // 2. MySQL 서버와 연결한다.
        String url = "jdbc:mysql://localhost:3306/smdb";
        String sqlid = "smuser";
        String sqlpwd = "111111";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, sqlid, sqlpwd);
            System.out.println("Connected to database");

            // 3. 전체 데이터 조회
            selectAllProducts(conn);

            // 4. 검색 기능
            searchProducts(conn, "상품", 50, 200, "2023-01-01", "2023-12-31");

        } catch (SQLException e) {
            System.out.println("Connection failed or SQL error");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 전체 데이터 조회 메소드
    private static void selectAllProducts(Connection conn) {
        String selectAllSql = "SELECT * FROM product";
        try (PreparedStatement ps = conn.prepareStatement(selectAllSql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("상품명 : " + rs.getString("name"));
                System.out.println("가격 : " + rs.getInt("price"));
                System.out.println("사진명 : " + rs.getString("image_name"));
                System.out.println("등록일자 : " + rs.getTimestamp("created_at"));
                System.out.println("수정일자 : " + rs.getTimestamp("updated_at"));
                System.out.println("등록자 : " + rs.getString("created_by"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 검색 기능 메소드
    private static void searchProducts(Connection conn, String nameSearch, int minPrice, int maxPrice, String startDate, String endDate) {
        String searchSql = "SELECT * FROM product WHERE name LIKE ? AND price BETWEEN ? AND ? AND created_at BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(searchSql)) {
            ps.setString(1, "%" + nameSearch + "%"); // 이름 검색 조건
            ps.setInt(2, minPrice); // 최소 가격
            ps.setInt(3, maxPrice); // 최대 가격
            ps.setString(4, startDate); // 등록일자 시작
            ps.setString(5, endDate);   // 등록일자 끝

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Price: " + rs.getInt("price"));
                    System.out.println("Image Name: " + rs.getString("image_name"));
                    System.out.println("Created At: " + rs.getTimestamp("created_at"));
                    System.out.println("Updated At: " + rs.getTimestamp("updated_at"));
                    System.out.println("Created By: " + rs.getString("created_by"));
                    System.out.println("------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
