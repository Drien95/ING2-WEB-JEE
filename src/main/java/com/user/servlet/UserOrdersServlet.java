package com.user.servlet;

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

@WebServlet(name = "userOrdersServlet", value = "/utilisateur/commandes")
public class UserOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");
        String orderParameter = request.getParameter("id");

        if (orderParameter == null || orderParameter.isEmpty()) {
            try {
                OrderDAOImpl dao_order = new OrderDAOImpl(DBConnect.getConn());
                List<Order> l_order = dao_order.getOrderUser(LOGIN_USER.getId());
                session.setAttribute("l_order",l_order);
            }catch (Exception e){
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/user/commandes.jsp").forward(request, response);
        }else{
            try {
                CartDAOImpl dao_cart = new CartDAOImpl(DBConnect.getConn());
                int orderId = Integer.parseInt(orderParameter);
                List<Cart> l_cart = dao_cart.getCart(orderId);
                System.out.println(l_cart);
                session.removeAttribute("l_cart");
                session.setAttribute("l_cart",l_cart);
            }catch (Exception e){
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/user/orderDetails.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
