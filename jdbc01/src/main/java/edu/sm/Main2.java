package edu.sm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {
        // 1. MySQL JDBC Driver를 로딩한다.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // 2. MySQL 을 서버와 연결한다.
        String url = "jdbc:mysql://localhost:3306/smdb";
        String sqlid = "smuser";
        String sqlpwd = "111111";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, sqlid, sqlpwd);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        // 3. SQL문으로 삭제 프로그램을 만든다.
        String deleteSql = "delete from cust where id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(deleteSql);
            ps.setString(1, "id06");
            int result = ps.executeUpdate();
            System.out.println(result);
            System.out.println("Delete result from database");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // 4. close
    }
}