package workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
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

            // 3. SQL문으로 추가 프로그램을 만든다.
            String insertSql = "INSERT INTO product (name, price, image_name, created_by) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, "녹차라떼");
                ps.setInt(2, 7000);
                ps.setString(3, "녹차라떼.jpg");
                ps.setString(4, "이태빈");
                int result = ps.executeUpdate();
                System.out.println(result + "개의 상품이 추가되었습니다.");
            }
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
}
