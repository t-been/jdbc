package edu.sm.cust;

import edu.sm.dto.Cust;
import edu.sm.service.CustService;

public class CustDelete {
    public static void main(String[] args) {
        CustService custService = new CustService();
        int id = 1;
        Cust cust = Cust.builder()
                .id(id)
                .build();

        try {
            custService.remove(cust.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}