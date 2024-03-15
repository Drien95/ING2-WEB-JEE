package com.seller;

import com.DAO.ProductDAOimpl;
import com.DB.DBConnect;
import com.entity.Product;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "sellerAddProductServlet", value = "/utilisateur/vendeur/ajouter_produit")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/seller/add_product.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            HttpSession session = request.getSession();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");

            String pName = request.getParameter("pName");
            String pers = request.getParameter("pers");
            String cat = "";


            if ("New".equals(pers)) {
                cat = request.getParameter("cat");
            } else {
                cat = request.getParameter("pers");
            }


            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int user_id = LOGIN_USER.getId();

            Product product = new Product(pName,cat,price,quantity,user_id);

            ProductDAOimpl dao = new ProductDAOimpl(DBConnect.getConn());

            boolean f = dao.addProduct(product);

            if(f){
                request.setAttribute("msg_success", "Produit ajouté avec succès");
            }else{
                request.setAttribute("msg_failed", "Probleme d'ajout server");
            }


            System.out.println(product + pers);
        }catch (Exception e){
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/seller/add_product.jsp").forward(request, response);
    }
}
