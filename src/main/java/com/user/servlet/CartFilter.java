package com.user.servlet;

import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/panier/*")
public class CartFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("LOGIN_USER") != null) {
            chain.doFilter(request, response);
        } else {
            // Redirigez vers la page de connexion si l'utilisateur n'est pas connecté
            httpResponse.sendRedirect("/login");
        }
    }
}
