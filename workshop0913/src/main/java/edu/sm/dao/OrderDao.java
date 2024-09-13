package edu.sm.dao;

import edu.sm.dto.Order;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements Dao<Integer, Order> {

    @Override
    public Order insert(Order order, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertOrders);
            ps.setInt(1, order.getCart_Id());
            ps.setObject(2, order.getOrder_Date());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return order;
    }

    @Override
    public Order update(Order order, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateOrders);
            ps.setInt(1, order.getCart_Id());
            ps.setObject(2, order.getOrder_Date());
            ps.setInt(3, order.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return order;
    }

    @Override
    public Boolean delete(Integer integer, Connection con) throws Exception {
        boolean result = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteOrders);
            ps.setInt(1, integer);
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    return result;
    }

    @Override
    public Order select(Integer integer, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = con.prepareStatement(Sql.selectOrders);
            ps.setInt(1, integer);
            rs = ps.executeQuery();
            if (rs.next()) {
                order = Order.builder()
                        .id(rs.getInt("id"))
                        .cart_Id(rs.getInt("cart_id"))
                        .order_Date(rs.getObject("order_date", java.time.LocalDateTime.class))
                        .build();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return order;
    }

    @Override
    public List<Order> select(Connection con) throws Exception {
        List<Order> orders = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.selectOrders);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = Order.builder()
                        .id(rs.getInt("id"))
                        .cart_Id(rs.getInt("cart_id"))
                        .order_Date(rs.getObject("order_date", java.time.LocalDateTime.class))
                        .build();
                orders.add(order);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return orders;
    }
}
