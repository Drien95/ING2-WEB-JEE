
<%@ page import="com.DAO.ProductDAOimpl" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
    ProductDAOimpl dao_nav = new ProductDAOimpl(DBConnect.getConn());
    List<String> list_cat = dao_nav.getAllCat();
%>
<style>
    .navbar-nav .nav-item:hover .nav-link,
    .navbar-nav .nav-item:hover .dropdown-item,
    .navbar-nav .nav-item.active .nav-link {
        border-radius: 0.25rem; /* Vous pouvez ajuster la valeur selon vos préférences */
    }
    .navbar {
        border-bottom: 2px solid #3498db; /* Couleur de la ligne bleu néon */
    }

    .btn-elegant {
        background-color: #3498db; /* Couleur de fond du bouton */
        color: #ffffff; /* Couleur du texte du bouton */
        border: 1px solid #3498db; /* Bordure du bouton */
        border-radius: 0.25rem; /* Bords arrondis du bouton */
        transition: background-color 0.3s, color 0.3s, border-color 0.3s; /* Effet de transition fluide */
    }

    .btn-elegant:hover {
        background-color: #ffffff; /* Couleur de fond au survol */
        color: #3498db; /* Couleur du texte au survol */
        border-color: #3498db; /* Bordure au survol */
    }
</style>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #000000;">
    <div class="container-fluid">
        <a class="navbar-brand" href="/" style="color: #3498db;"><strong>NightShop</strong></a>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="border-color: #3498db;color: #3498db">
                        Categories
                    </a>
                    <ul class="dropdown-menu" style="background-color: #000000;">
                        <%
                            for(String c : list_cat){
                        %>
                        <li><a class="dropdown-item" href="/categorie?name=<%=c%>" style="color: #3498db;"><%=c%></a></li>
                        <%
                            }
                        %>
                    </ul>
                </li>


            </ul>

            <ul class="navbar-nav ml-auto">
                <c:if test="${LOGIN_USER != null && LOGIN_USER.getRole() eq 'admin'}">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/" style="color: #3498db; display: flex; align-items: center;">
                            <span>Admin</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${LOGIN_USER != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/utilisateur/" style="color: #3498db; display: flex; align-items: center;">
                            <i class="fa-regular fa-user fa-xl" style="margin-right: 5px;"></i>
                            <span>${LOGIN_USER.name}</span>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/panier" style="color: #3498db;margin-left: 5px">
                            <i class="fa-solid fa-cart-shopping fa-xl" style="margin-right: 5px;"></i>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="btn btn-elegant" href="/logout" style="margin-left: 10px;">Deconnexion</a>
                    </li>
                </c:if>
                <c:if test="${empty LOGIN_USER}">
                    <li class="nav-item">
                        <a class="btn btn-elegant" href="/login">Connexion</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-elegant" href="/register" style="margin-left: 10px;">Inscription</a>
                    </li>
                </c:if>
            </ul>

        </div>
    </div>
</nav>


