package com.DAO;

import com.entity.Order;
import com.entity.User;

import java.util.List;

public interface OrderDAO {

    public boolean updateOrder(int orderId, double ttc);
    public List<Order> getOrderUser(int userId);
    public List<Order> getAllOrder();
    public void deleteOrder(int userId);
}
