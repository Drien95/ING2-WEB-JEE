package com.categories;

import com.DAO.ProductDAOimpl;
import com.DB.DBConnect;
import com.entity.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "productServlet", value = "/produit")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
        try{

            // Vérifier si le NAME est présent dans la requête
            String idParameter = request.getParameter("id");
            HttpSession session = request.getSession();

            if (idParameter == null || idParameter.isEmpty()) {
                // Rediriger l'utilisateur vers une autre page ou afficher un message d'erreur
                response.sendRedirect("/");
                return;
            }

            ProductDAOimpl dao = new ProductDAOimpl(DBConnect.getConn());
            int id = Integer.parseInt(idParameter);

            Product product = dao.getProduct(id);
            if(product == null){
                response.sendRedirect("/");
                return;
            }

            session.setAttribute("product", product);

        }catch(Exception e){
            e.printStackTrace();
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
