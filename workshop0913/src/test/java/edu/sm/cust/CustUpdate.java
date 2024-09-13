
package edu.sm.cust;


import edu.sm.dto.Cust;
import edu.sm.service.CustService;

import java.sql.Date;

public class CustUpdate {
    public static void main(String[] args) {
        CustService custService = new CustService();
        int id = 1;
        Cust cust = Cust.builder()
                .id(id)
                .pwd("9876")
                .name("홍길동")
                .address("천안시 청수동")
                .phone("010-1234-5678")
                .birthdate(Date.valueOf("2001-04-10"))
                .build();

        try {
            custService.modify(cust);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
