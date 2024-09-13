package edu.sm.frame;

public class Sql {
    public static String insertCust = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static String selectCust = "SELECT * FROM Customer";
    public static String selectOneCust = "SELECT * FROM Customer WHERE id = ?";
    public static String deleteCust = "DELETE FROM Customer WHERE id = ?";
    public static String updateCust = "UPDATE Customer SET pwd=?, name = ?, address=?, phone=?, birthdate=? WHERE id = ?";

    public static String insertProduct = "INSERT INTO product VALUES (?,?,?,?)";
    public static String selectProduct = "SELECT * FROM product";
    public static String selectOneProduct = "SELECT * FROM product WHERE id = ?";
    public static String deleteProduct = "DELETE FROM product WHERE id = ?";
    public static String updateProduct = "UPDATE product SET name = ?, price = ? WHERE id = ?";

    public static String insertOrders = "INSERT INTO orders (cart_id, order_date) VALUES (?, ?)";
    public static String selectOrders = "SELECT * FROM orders";
    public static String selectOneOrders = "SELECT * FROM orders WHERE id = ?";
    public static String deleteOrders = "DELETE FROM orders WHERE id = ?";
    public static String updateOrders = "UPDATE orders SET order_date = ? WHERE id = ?";

    public static String insertCart = "INSERT INTO Cart (cId, pId, count) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE count = count + ?";
    public static String updateCart = "UPDATE Cart SET count = ? WHERE id = ?";
    public static String deleteCart = "DELETE FROM Cart WHERE id = ?";
    public static String selectOneCart = "SELECT * FROM Cart WHERE id = ?";
    public static String selectCart = "SELECT * FROM Cart";
    public static String selectByUserCart = "SELECT * FROM Cart WHERE cId = ?";
}