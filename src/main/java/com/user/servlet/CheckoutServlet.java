package com.user.servlet;

import com.DAO.CartDAOImpl;
import com.DAO.OrderDAOImpl;
import com.DAO.ProductDAOimpl;
import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.Gmail.GMailer;
import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.Authenticator;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "checkoutServlet", value = "/panier/validation")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/paiement.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            OrderDAOImpl dao = new OrderDAOImpl(DBConnect.getConn());
            CartDAOImpl dao2 = new CartDAOImpl(DBConnect.getConn());
            UserDAOImpl user_dao = new UserDAOImpl(DBConnect.getConn());
            ProductDAOimpl dao_product = new ProductDAOimpl(DBConnect.getConn());

            // Lire les informations du formulaire
            String nomCarte = request.getParameter("nom");
            String numeroCarte = request.getParameter("numerocarte");
            String dateExpiration = request.getParameter("dateexpiration");
            String codeCVV = request.getParameter("codecvv");



            User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");
            String commandes_total = String.format("Bonjour %s,\nVoici le récapitulatif de votre commande !", LOGIN_USER.getName());
            commandes_total += "\nPRODUIT    -     QUANTITE   -   PRIX TOTAL\n";

            session.removeAttribute("ORDER_ID");


            int ORDER_ID = dao2.getActiveOrderId(LOGIN_USER.getId());
            session.setAttribute("ORDER_ID",ORDER_ID);
            int product_quantity = 0;
            int product_new_quantity = 0;

            double ttc = 0;

            List<Cart> cartItems = dao2.getCartItems(LOGIN_USER.getId());

            Product product = new Product();

            System.out.println("orderid = "+ORDER_ID+"login = "+LOGIN_USER);

            for(Cart cart : cartItems){
                //calcul du ttc
                ttc = ttc +  (cart.getQuantity() * cart.getPrice());
                //calcul pour avoir la nouvelle quantite
                product_quantity = dao_product.getProduct(cart.getProduct_id()).getQuantity();
                product_new_quantity = product_quantity - cart.getQuantity();
                //set du Product
                product.setQuantity(cart.getQuantity());
                product.setId(cart.getProduct_id());
                product.setQuantity(product_new_quantity);
                //update de la quantite du Product apres achat
                dao_product.updateQuantity(product);
                commandes_total += String.format(" %s :  %2d : %,.2f €\n",dao_product.getProduct(cart.getProduct_id()).getName(),cart.getQuantity(),cart.getQuantity() * cart.getPrice());
            }

            commandes_total += String.format("\nTOTAL TTC :  %,.2f €\n",ttc);
            commandes_total += "\nMerci pour votre Achat";

            boolean f = dao.updateOrder(ORDER_ID,ttc);
            boolean fidelity_check = user_dao.addFidelityPoint(LOGIN_USER.getId(), ttc*0.1);

            if(f){
                new GMailer().sendMail("Confirmation Commande",commandes_total ,LOGIN_USER.getEmail());
                request.setAttribute("msg_success", "Commande passé avec succes !");
                session.removeAttribute("ORDER_ID");
            }else{
                request.setAttribute("msg_failed", "Probleme server");
            }


        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les erreurs ici
        }
        getServletContext().getRequestDispatcher("/paiement.jsp").forward(request, response);

    }

}
