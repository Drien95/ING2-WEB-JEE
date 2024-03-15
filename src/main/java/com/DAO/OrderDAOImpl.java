package com.DAO;

import com.entity.Order;
import com.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{

    private Connection conn;

    public OrderDAOImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public boolean updateOrder(int orderId, double ttc) {
        boolean f = false;

        System.out.println(orderId);
        try{
            String sql = "update orders set state=2,price=?,order_date=CURRENT_DATE where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(2, orderId);
            ps.setDouble(1, ttc);

            int i = ps.executeUpdate();

            if(i==1){
                f = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return f;

    }

    @Override
    public List<Order> getOrderUser(int userId) {
        List<Order> l_order = new ArrayList<Order>();
        Order order = null;

        try{

            String sql = "select * from orders where user_id=? and state=2";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                order = new Order();
                order.setOrder_date(rs.getString("order_date"));
                order.setUser_id(userId);
                order.setPrice(rs.getDouble("price"));
                order.setState(2);
                order.setMethod_payment(rs.getString("method_payment"));
                order.setId(rs.getInt("id"));

                l_order.add(order);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return l_order;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> l_order = new ArrayList<Order>();
        Order order = null;

        try{

            String sql = "select * from orders WHERE state=2";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                order = new Order();
                order.setOrder_date(rs.getString("order_date"));
                order.setPrice(rs.getDouble("price"));
                order.setState(2);
                order.setMethod_payment(rs.getString("method_payment"));
                order.setId(rs.getInt("id"));

                l_order.add(order);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return l_order;
    }

    @Override
    public void deleteOrder(int userId) {
        try{
            String sql = "DELETE FROM orders WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            int i = ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
