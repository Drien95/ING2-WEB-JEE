package com.admin;

import com.DAO.ProductDAOimpl;
import com.DB.DBConnect;
import com.entity.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "editProductServlet", value = "/admin/modifier_produit")
public class EditProductServlet extends HttpServlet {

    Product product = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/edit_product.jsp");
        try{

            // Vérifier si l'ID est présent dans la requête
            String idParameter = request.getParameter("id");
            if (idParameter == null || idParameter.isEmpty()) {
                // Rediriger l'utilisateur vers une autre page ou afficher un message d'erreur
                response.sendRedirect("/admin");
                return;
            }

            ProductDAOimpl dao = new ProductDAOimpl(DBConnect.getConn());
            int id = Integer.parseInt(idParameter);

            this.product = dao.getProduct(id);
            if(this.product == null){
                response.sendRedirect("/admin");
                return;
            }

            request.setAttribute("product",this.product);

        }catch(Exception e){
            e.printStackTrace();
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String pName = request.getParameter("pName");
            String pers = request.getParameter("pers");
            String cat = "";

            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int product_id = Integer.parseInt(request.getParameter("id"));
            int user_id = 1;

            double price = Double.parseDouble(request.getParameter("price"));


            if ("New".equals(pers)) {
                cat = request.getParameter("cat");
            } else {
                cat = request.getParameter("pers");
            }

            this.product = null;
            this.product = new Product(pName,cat,price,quantity,user_id);
            this.product.setId(product_id);

            ProductDAOimpl dao = new ProductDAOimpl(DBConnect.getConn());
            boolean f = dao.updateEditProduct(this.product);

            request.setAttribute("product", this.product);

            if(f){
                request.setAttribute("msg_success", "Produit modifié avec succès");
            }else{
                request.setAttribute("msg_failed", "Probleme dans le server");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/admin/edit_product.jsp").forward(request, response);
    }
}
