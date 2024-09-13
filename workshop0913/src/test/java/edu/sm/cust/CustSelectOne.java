package edu.sm.cust;

import edu.sm.dto.Cust;
import edu.sm.service.CustService;

public class CustSelectOne {

    public static void main(String[] args) {
        CustService custService = new CustService();
        int id = 2;
        Cust cust = null;

        try {
            cust = custService.get(id);
            System.out.println(cust);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}