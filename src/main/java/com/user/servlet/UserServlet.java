package com.user.servlet;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "userServlet", value = "/utilisateur/infos")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/user/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formName = request.getParameter("formName");
        System.out.println(formName);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");


        if("form_infos".equals(formName)){

            try {

                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("nom") != null ? request.getParameter("nom"):user.getName();
                String address = request.getParameter("adresse") != null ? request.getParameter("adresse"):user.getAdress();
                String city = request.getParameter("ville") != null ? request.getParameter("ville"):user.getCity();
                int postal = Integer.parseInt(request.getParameter("codePostal")) != 0 ? Integer.parseInt(request.getParameter("codePostal")) : user.getPostal();


                User user2 = new User();
                user2.setId(id);
                user2.setName(name);
                user2.setPassword(user.getPassword());
                user2.setEmail(user.getEmail());
                user2.setAdress(address);
                user2.setPostal(postal);
                user2.setCity(city);

                System.out.println(user2);

                UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
                boolean f = dao.updateEditUser(user2);

                if(f){
                    request.setAttribute("msg_success", "Changement réalisé avec succes !");
                    session.removeAttribute("LOGIN_USER");
                    session.setAttribute("LOGIN_USER",dao.login(user2.getEmail(), user.getPassword()));
                }else{
                    request.setAttribute("msg_failed", "Probleme server");
                }


            } catch (Exception e) {
                e.printStackTrace();
                // Gérer les erreurs ici
            }

        } else if ("form_mail".equals(formName)) {
            try {
                String mail = request.getParameter("mail");
                int id = Integer.parseInt(request.getParameter("id"));

                User user2 = new User();
                user2.setEmail(mail);
                user2.setId(id);

                UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
                boolean b_check_user = dao.checkUser(mail);
                System.out.println(user2);

                if(b_check_user){

                    boolean f = dao.updateEditUserMail(user2);
                    if(f){
                        request.setAttribute("msg_success", "mail changé avec succes ! !");
                        session.removeAttribute("LOGIN_USER");
                        session.setAttribute("LOGIN_USER",dao.login(user2.getEmail(), user.getPassword()));
                    }else {
                        request.setAttribute("msg_failed", "Probleme server");
                    }

                }else{
                    System.out.println("Un compte avec le même email existe déjà");
                    request.setAttribute("msg_failed", "Un compte avec le même email existe déjà");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        } else if ("form_pwd".equals(formName)) {

            try {

                String pwd = request.getParameter("pwd");
                String pwd_verif = request.getParameter("pwd_verif");

                if(!(pwd.equals(pwd_verif))){
                    request.setAttribute("msg_failed", "votre mod de passe doit être le même dans les deux champs");
                }else{

                    UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());

                    int id = Integer.parseInt(request.getParameter("id"));

                    User user2 = new User();
                    user2.setPassword(dao.doHashPassword(pwd));
                    user2.setId(id);

                    boolean f = dao.updateEditUserPwd(user2);
                    if (f) {
                        request.setAttribute("msg_success", "mot de passe changé avec succes !");
                        session.removeAttribute("LOGIN_USER");
                        session.setAttribute("LOGIN_USER",dao.login(user.getEmail(), user2.getPassword()));
                    } else {
                        request.setAttribute("msg_failed", "Probleme server");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        getServletContext().getRequestDispatcher("/user/user.jsp").forward(request, response);
    }
}
