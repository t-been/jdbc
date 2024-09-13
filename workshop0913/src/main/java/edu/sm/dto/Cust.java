package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cust {
    private int id; // PK
    private String email; // 이메일 (로그인)
    private String pwd; // 비번
    private String name; // 이름
    private String address; // 주소
    private Date regdate; // 생성날짜
    private String phone;
    private Date birthdate;
}