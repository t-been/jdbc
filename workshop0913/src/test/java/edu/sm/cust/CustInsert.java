package edu.sm.cust;

import edu.sm.dto.Cust;
import edu.sm.exception.DuplicatedEmailException;
import edu.sm.service.CustService;

import java.sql.Date;

public class CustInsert {

    public static void main(String[] args) {

        CustService custService = new CustService();
        Cust cust = Cust.builder() // id 입력 안해도 됨
                .email("taebin123@naver.com")
                .pwd("1234")
                .name("이태빈")
                .address("아산시 탕정")
                .phone("010-1234-1234")
                .birthdate(Date.valueOf("2001-08-01"))
                .build();

        try {
            custService.add(cust);
        } catch (DuplicatedEmailException e) {
            System.out.println("해당 Email은 이미 존재합니다.");
        } catch (Exception e) {
            System.out.println("시스템 장애 발생");
            e.printStackTrace(); // 개발자 테스트에선 이것이 중요함
        }
    }
}