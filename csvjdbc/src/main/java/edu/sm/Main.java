package edu.sm;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // 1. MySQL JDBC Driver 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
            return;
        }

        // 2. MySQL 연결
        String url = "jdbc:mysql://localhost:3306/smdb";  // 데이터베이스 URL
        String sqlid = "smuser";  // 데이터베이스 사용자 ID
        String sqlpwd = "111111";  // 데이터베이스 사용자 비밀번호

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, sqlid, sqlpwd);
            System.out.println("Connected to database");

            // OpenCSV 사용하여 CSV 파일 읽기
            String csvFile = "d:/aa.csv";  // 업로드한 CSV 파일 경로
            CSVReader reader = new CSVReader(new FileReader(csvFile, Charset.forName("MS949")));

            // 첫 번째 줄(헤더) 읽기
            String[] headers = reader.readNext();
            if (headers != null) {
                createTable(conn, headers);  // 테이블 생성
            }

            // 데이터 삽입
            String insertSQL = generateInsertSQL(headers);
            insertData(conn, reader, insertSQL, headers.length);

            reader.close();

        } catch (SQLException | IOException | CsvValidationException e) {
            e.printStackTrace();
        } finally {
            // 자원 해제
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 테이블 생성 메서드
    private static void createTable(Connection conn, String[] headers) throws SQLException {
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS csv_table (");
        for (int i = 0; i < headers.length; i++) {
            createTableSQL.append("`").append(headers[i]).append("` VARCHAR(255)");  // 백틱으로 컬럼 이름을 감쌈
            if (i < headers.length - 1) {
                createTableSQL.append(", ");
            }
        }
        createTableSQL.append(")");

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL.toString());
            System.out.println("Table created successfully.");
        }
    }

    // 데이터 삽입 메서드
    private static void insertData(Connection conn, CSVReader reader, String insertSQL, int columnCount) throws SQLException, IOException, CsvValidationException {
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            String[] values;
            while ((values = reader.readNext()) != null) {
                if (values.length != columnCount) {
                    // 컬럼 수와 데이터 수가 일치하지 않으면 경고 출력
                    System.out.println("데이터와 테이블의 컬럼 수가 일치하지 않음. 데이터를 건너뜁니다.");
                    continue;
                }
                for (int i = 0; i < values.length; i++) {
                    pstmt.setString(i + 1, values[i].trim());
                }
                pstmt.executeUpdate();
            }
            System.out.println("Data inserted successfully.");
        }
    }

    // 데이터 삽입 SQL 생성 메서드
    private static String generateInsertSQL(String[] headers) {
        StringBuilder insertSQL = new StringBuilder("INSERT INTO csv_table (");
        for (int i = 0; i < headers.length; i++) {
            insertSQL.append("`").append(headers[i]).append("`");
            if (i < headers.length - 1) {
                insertSQL.append(", ");
            }
        }
        insertSQL.append(") VALUES (");
        for (int i = 0; i < headers.length; i++) {
            insertSQL.append("?");
            if (i < headers.length - 1) {
                insertSQL.append(", ");
            }
        }
        insertSQL.append(")");
        return insertSQL.toString();
    }
}
