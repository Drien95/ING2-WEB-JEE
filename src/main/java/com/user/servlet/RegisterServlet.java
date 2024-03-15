package com.user.servlet;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.Gmail.GMailer;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            String name = request.getParameter("fname");
            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");



            System.out.println(
                    name + " " + email +" " + pwd
            );


            UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
            String hash_pwd = dao.doHashPassword(pwd);
            boolean b_check_user = dao.checkUser(email);

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(hash_pwd);

            if(b_check_user){
                boolean b_user_register = dao.userRegister(user);

                if(b_user_register){
                    new GMailer().sendMail("Creation de compte","Votre compte a été créé avec succès !", user.getEmail());
                    System.out.println("Creation de compte avec succes");
                    request.setAttribute("msg_success", "Creation de compte avec succes !");
                }else {
                    System.out.println("Quelque chose c'est mal passé avec le server");
                    request.setAttribute("msg_failed", "Quelque chose c'est mal passé avec le server");
                }
            }else{
                System.out.println("Un compte avec le même email existe déjà");
                request.setAttribute("msg_failed", "Un compte avec le même email existe déjà");
            }



        }catch(Exception e){
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
