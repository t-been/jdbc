package edu.sm.service;

import edu.sm.dao.CartDao;
import edu.sm.dto.Cart;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartService implements MService<Integer, Cart> {

    CartDao dao;
    ConnectionPool cp;

    public CartService() {
        dao = new CartDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 장바구니에 상품 추가
    @Override
    public Cart add(Cart cart) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false); // 트랜잭션 시작
            dao.insert(cart, con); // 같은 상품이 있으면 count 증가
            con.commit();
        } catch (Exception e) {
            con.rollback(); // 트랜잭션 롤백
            throw e;
        } finally {
            cp.releaseConnection(con); // 커넥션 해제
        }
        return cart;
    }

    // 장바구니의 상품 수량 변경
    @Override
    public Cart modify(Cart cart) throws Exception {
        Connection con = cp.getConnection();
        try {
            dao.update(cart, con); // 수량 업데이트
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return cart;
    }

    // 장바구니에서 특정 상품 제거
    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        boolean result;
        try {
            result = dao.delete(id, con); // 상품 삭제
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    // 특정 상품 조회 (장바구니 내에서)
    @Override
    public Cart get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Cart cart;
        try {
            cart = dao.select(id, con); // 특정 상품 조회
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return cart;
    }

    // 모든 장바구니 아이템 조회 (해당 고객의 모든 상품)
    @Override
    public List<Cart> get() throws Exception {
        Connection con = cp.getConnection();
        List<Cart> carts;
        try {
            carts = dao.select(con); // 모든 장바구니 아이템 조회
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return carts;
    }

    public List<Cart> getCartByCustomerId(Integer cId) throws Exception {
        Connection con = cp.getConnection();
        List<Cart> carts;
        try {
            carts = dao.selectByCustomerId(cId, con);  // 고객 ID 기반으로 장바구니 상품 조회
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return carts;
    }


}