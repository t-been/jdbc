package edu.sm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.CompletionException;

public class Test {
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
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (CompletionException e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
