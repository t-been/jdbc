package edu.sm;

import java.sql.*;

public class Main5 {
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
        // 전체 데이터를 가져오기
        String selectAllSql = "select * from cust";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(selectAllSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("pwd"));
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
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
    }
}
