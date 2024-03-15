package com.admin;

import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("LOGIN_USER") != null) {
            User user = (User) session.getAttribute("LOGIN_USER");

            // Vérifiez le rôle de l'utilisateur
            if ("admin".equals(user.getRole())) {
                // L'utilisateur a le rôle "admin", permettez l'accès
                chain.doFilter(request, response);
            } else {
                // Redirigez vers une page d'erreur ou une autre page appropriée
                httpResponse.sendRedirect("/");
            }
        } else {
            // Redirigez vers la page de connexion si l'utilisateur n'est pas connecté
            httpResponse.sendRedirect("/login");
        }
    }

}
