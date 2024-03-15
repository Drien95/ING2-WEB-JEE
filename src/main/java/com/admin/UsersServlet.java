package com.admin;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "usersServlet", value = "/admin/utilisateurs")
public class UsersServlet extends HttpServlet {

    private List<User> list_user;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/users.jsp");

        try{

            UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
            List<User> lu = dao.getAllUser();
            setList_user(lu);

            request.setAttribute("list_user", getList_user());

        }catch (Exception e){
            e.printStackTrace();
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formName = request.getParameter("formName");
        System.out.println(formName);

        if ("form_del".equals(formName)) {

            try{

                int id = Integer.parseInt(request.getParameter("id"));
                System.out.println(id);
                UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());


                boolean f = dao.deleteUser(id);

                if(f){
                    request.setAttribute("msg_success", "Utilisateurs supprimé avec succès !");
                    List<User> lu = dao.getAllUser();
                    setList_user(lu);
                }else{
                    request.setAttribute("msg_failed", "Probleme dans le server");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        } else if ("form_role".equals(formName)) {
            System.out.println("role");
            try{

                int id = Integer.parseInt(request.getParameter("id"));
                String role = request.getParameter("role");

                UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());

                User user = new User();
                user.setId(id);
                user.setRole(role);

                boolean f = dao.updateEditUserRole(user);

                if(f){
                    request.setAttribute("msg_success", "Role modifié avec succès");
                    List<User> lu = dao.getAllUser();
                    setList_user(lu);
                }else{
                    request.setAttribute("msg_failed", "Probleme dans le server");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        request.setAttribute("list_user", getList_user());
        getServletContext().getRequestDispatcher("/admin/users.jsp").forward(request, response);

    }

    public List<User> getList_user() {
        return list_user;
    }

    public void setList_user(List<User> list_user) {
        this.list_user = list_user;
    }
}
