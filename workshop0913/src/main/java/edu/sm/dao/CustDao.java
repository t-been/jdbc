package edu.sm.dao;

import edu.sm.dto.Cust;
import edu.sm.exception.DuplicatedEmailException;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CustDao implements Dao<Integer, Cust> {
    @Override
    public Cust insert(Cust cust, Connection con) throws  Exception {
        // 회원가입
        PreparedStatement ps = null;
        try { // 아래 try, catch, finally 로직 예시는 굉장히 굉장히 굉장히 중요하다.
            ps = con.prepareStatement(Sql.insertCust);
            ps.setInt(1, cust.getId());
            ps.setString(2, cust.getEmail());
            ps.setString(3, cust.getPwd());
            ps.setString(4, cust.getName());
            ps.setString(5, cust.getAddress());
            ps.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setString(7, cust.getPhone());
            ps.setDate(8, cust.getBirthdate());
            ps.executeUpdate(); // 에러 발생 가능성 존재
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedEmailException();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

        return cust;
    }

    @Override
    public Cust update(Cust cust, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateCust);
            ps.setString(1, cust.getPwd());
            ps.setString(2, cust.getName());
            ps.setString(3, cust.getAddress());
            ps.setString(4, cust.getPhone());
            ps.setDate(5, cust.getBirthdate());
            ps.setInt(6, cust.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return cust;
    }

    @Override
    public Boolean delete(Integer i, Connection con) throws Exception {
        boolean flag = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteCust);
            ps.setInt(1, i);
            int result =  ps.executeUpdate();
            if (result == 1) {
                flag = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return flag;
    }

    @Override
    public Cust select(Integer i, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cust cust = null;
        try {
            ps = con.prepareStatement(Sql.selectOneCust);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            rs.next();
            cust = new Cust();
            cust.setId(rs.getInt("id"));
            cust.setEmail(rs.getString("email"));
            cust.setPwd(rs.getString("pwd"));
            cust.setName(rs.getString("name"));
            cust.setAddress(rs.getString("address"));
            cust.setRegdate(rs.getDate("signup_date"));
            cust.setPhone(rs.getString("phone"));
            cust.setBirthdate(rs.getDate("birthdate"));
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return cust;
    }

    @Override
    public List<Cust> select(Connection con) throws Exception {
        List<Cust> custs = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectCust);
            rs = ps.executeQuery();
            while (rs.next()) {
                custs.add(new Cust(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("pwd"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getDate("signup_date"),
                        rs.getString("phone"),
                        rs.getDate("birthdate")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return custs;
    }

}