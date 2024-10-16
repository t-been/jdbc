package edu.sm.service;

import edu.sm.dao.OrderDao;
import edu.sm.dto.Order;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.util.List;

public class OrderService implements MService<Integer, Order> {

    OrderDao dao;
    ConnectionPool cp;

    public OrderService() {
        dao = new OrderDao();
        try {
            cp = ConnectionPool.create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order add(Order order) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(order, con);
            con.commit();
            System.out.println("Send SMS to: " + order.getCart_Id());
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return order;
    }

    @Override
    public Order modify(Order order) throws Exception {
        Connection con = cp.getConnection();
        try {
            dao.update(order, con);
            System.out.println("Send (Updated) SMS to: " + order.getCart_Id());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return order;
    }

    @Override
    public Boolean remove(Integer integer) throws Exception {
        Connection con = cp.getConnection();
        boolean result = false;
        try {
            dao.delete(integer, con);
            con.commit();
            result = false;
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Order get(Integer integer) throws Exception {
        Connection con = cp.getConnection();
        Order order = null;
        try {
            order = dao.select(integer, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return order;
    }

    @Override
    public List<Order> get() throws Exception {
        Connection con = cp.getConnection();
        List<Order> list = null;
        try {
            list = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return list;
    }
}
