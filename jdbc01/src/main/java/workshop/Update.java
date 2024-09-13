package workshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    public static void main(String[] args) {
        // 1. MySQL JDBC Driver를 로딩한다.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없습니다.");
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
            System.out.println("데이터베이스에 연결되었습니다.");

            // 3. 상품 수정 SQL문을 실행한다.
            String updateSql = "UPDATE product SET name = ?, price = ?, image_name = ? WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                ps.setString(1, "따듯한커피");
                ps.setInt(2, 3000); // 가격을 정수로 설정
                ps.setString(3, "따뜻한커피.jpg");
                ps.setInt(4, 3); // 수정할 상품의 ID
                int result = ps.executeUpdate();
                System.out.println(result + "개의 상품이 수정 되었습니다.");
            }
        } catch (SQLException e) {
            System.out.println("연결 실패 또는 SQL 오류");
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
