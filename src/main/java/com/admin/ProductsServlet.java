package com.admin;

import com.DAO.ProductDAOimpl;
import com.DB.DBConnect;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "productsServlet", value = "/admin/produits")
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/products.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int product_id = Integer.parseInt(request.getParameter("id"));

            ProductDAOimpl dao = new ProductDAOimpl(DBConnect.getConn());
            boolean f = dao.deleteProduct(product_id);

            System.out.println(product_id);

            if(f){
                request.setAttribute("msg_success", "Produit supprimé avec succès");
            }else{
                request.setAttribute("msg_failed", "Probleme de server");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/admin/products.jsp").forward(request, response);
    }
}
