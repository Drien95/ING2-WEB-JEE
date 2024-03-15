package com.user.servlet;

import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.Cart;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "cartServlet", value = "/panier")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("LOGIN_USER");
            if (loginUser != null) {
                int user_id = loginUser.getId();

                CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
                List<Cart> cartItems = dao.getCartItems(user_id);

                request.setAttribute("cartItems", cartItems);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("/login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");


            if (LOGIN_USER != null) {

                CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());

                int user_id = LOGIN_USER.getId();
                int product_id = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int order_id = dao.getActiveOrderId(user_id);

                boolean success = false;

                String message = "";
                String action = request.getParameter("action");

                Cart cart = new Cart(product_id, quantity);


                System.out.println(product_id +""+user_id +""+quantity+" "+order_id);

                switch (action) {
                    case "add":
                        System.out.println("add Case");
                        success = dao.addToCart(cart, user_id);
                        session.setAttribute("ORDER_ID",order_id);
                        message = success ? "Produit ajouté au panier !" : "maximum de quantité atteinte !";
                        break;
                    case "decrement":
                        success = dao.decrementQuantity(cart, user_id);
                        message = success ? "Quantité décrémentée !" : "maximum de quantité atteinte !";
                        break;
                    case "increment":
                        success = dao.incrementQuantity(cart, user_id);
                        message = success ? "Quantité incrémentée !" : "minimum de quantité atteinte !";
                        break;
                    case "delete":
                        dao.deleteCartItem(cart, order_id);
                        success = true;
                        message = "produit supprime";
                        break;
                    case "updateQuantity":
                        System.out.println("updateQuantity case");
                        dao.updateQuantityInCart(cart.getProduct_id(),order_id,quantity);
                        success = true;
                        message = "quantite modifie !";
                }

                List<Cart> cartItems = dao.getCartItems(user_id);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                String jsonResponse = String.format("{\"success\": %s, \"message\": \"%s\"}",success, message);
                response.getWriter().write(jsonResponse);

            } else {
                response.sendRedirect("/login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
