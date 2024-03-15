package com.categories;

import com.DAO.ProductDAOimpl;
import com.DB.DBConnect;
import com.entity.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "catServlet", value = "/categorie")
public class CatServlet extends HttpServlet {

    private List<Product> list_product = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/categorie.jsp");
        try{

            // Vérifier si le NAME est présent dans la requête
            String nameParameter = request.getParameter("name");
            HttpSession session = request.getSession();

            if (nameParameter == null || nameParameter.isEmpty()) {
                // Rediriger l'utilisateur vers une autre page ou afficher un message d'erreur
                response.sendRedirect("/");
                return;
            }

            ProductDAOimpl dao = new ProductDAOimpl(DBConnect.getConn());


            this.list_product = dao.getProductByCat(nameParameter);
            if(this.list_product == null){
                response.sendRedirect("/");
                return;
            }

            session.setAttribute("cat", nameParameter);
            session.setAttribute("list_product",getList_product());

        }catch(Exception e){
            e.printStackTrace();
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public List<Product> getList_product() {
        return list_product;
    }

    public void setList_product(List<Product> list_product) {
        this.list_product = list_product;
    }
}
