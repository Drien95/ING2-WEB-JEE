package com.user.servlet;

import com.DAO.CartDAOImpl;
import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/connection.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
            CartDAOImpl dao2 = new CartDAOImpl(DBConnect.getConn());


            HttpSession session = request.getSession();

            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");

            User user = dao.login(email, dao.doHashPassword(pwd));
            System.out.println(user);

            if(user != null){
//                request.setAttribute("user", user);
                session.setAttribute("LOGIN_USER", user);
                int ORDER_ID = dao2.getActiveOrderId(user.getId());
                session.setAttribute("ORDER_ID",ORDER_ID);
                response.sendRedirect("/");
            }else{
                request.setAttribute("msg_failed", "Email ou mot de passe invalide");
                getServletContext().getRequestDispatcher("/connection.jsp").forward(request, response);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
