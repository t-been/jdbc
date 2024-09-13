package edu.sm.dao;

import edu.sm.dto.Cart;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements Dao<Integer, Cart> {

    @Override
    public Cart insert(Cart cart, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            // 상품이 이미 장바구니에 있으면 count만 증가
            ps = con.prepareStatement(Sql.insertCart);
            ps.setInt(1, cart.getCId());
            ps.setInt(2, cart.getPId());
            ps.setInt(3, cart.getCount());
            ps.setInt(4, cart.getCount()); // 이미 존재하는 경우 count 증가
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return cart;
    }

    @Override
    public Cart update(Cart cart, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateCart);
            ps.setInt(1, cart.getCount());
            ps.setInt(2, cart.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return cart;
    }

    @Override
    public Boolean delete(Integer id, Connection con) throws Exception {
        boolean flag = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteCart);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
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
    public Cart select(Integer id, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cart cart = null;
        try {
            ps = con.prepareStatement(Sql.selectOneCart);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cart = new Cart(rs.getInt("id"),
                        rs.getInt("cId"),
                        rs.getInt("pId"),
                        rs.getInt("count"));
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
        return cart;
    }

    @Override
    public List<Cart> select(Connection con) throws Exception {
        List<Cart> carts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectCart);
            rs = ps.executeQuery();
            while (rs.next()) {
                carts.add(new Cart(rs.getInt("id"),
                        rs.getInt("cId"),
                        rs.getInt("pId"),
                        rs.getInt("count")));
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
        return carts;
    }

    // 특정 사용자의 장바구니 현황
    public List<Cart> selectByCustomerId(Integer cId, Connection con) throws Exception {
        List<Cart> carts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectByUserCart);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            while (rs.next()) {
                carts.add(new Cart(rs.getInt("id"),
                        rs.getInt("cId"),
                        rs.getInt("pId"),
                        rs.getInt("count")));
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
        return carts;
    }

}