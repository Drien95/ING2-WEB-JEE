package com.admin;

import com.DAO.CartDAOImpl;
import com.DAO.OrderDAOImpl;
import com.DB.DBConnect;
import com.entity.Cart;
import com.entity.Order;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ordersServlet", value = "/admin/commandes")
public class OrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");

        try {
            OrderDAOImpl dao_order = new OrderDAOImpl(DBConnect.getConn());
            List<Order> l_order = dao_order.getAllOrder();
            session.setAttribute("l_order",l_order);
        }catch (Exception e){
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/admin/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
