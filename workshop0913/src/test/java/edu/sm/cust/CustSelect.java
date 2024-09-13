package edu.sm.cust;

import edu.sm.dto.Cust;
import edu.sm.service.CustService;

import java.util.List;

public class CustSelect {
    public static void main(String[] args) {
        CustService custService = new CustService();
        List<Cust> custs = null;
        try {
            custs = custService.get();
            System.out.println(custs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}