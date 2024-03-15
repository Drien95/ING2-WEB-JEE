<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/27/2023
  Time: 10:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.entity.User" %>
<%@ page import="com.entity.Cart" %>
<%@ page import="com.entity.Order" %>
<%@ page import="com.DAO.CartDAOImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");
  List<Order> l_order = (List<Order>) session.getAttribute("l_order");
  List<Cart> l_cart = (List<Cart>) session.getAttribute("l_cart");
%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="style/style.css">
  <link rel="stylesheet" type="text/css" href="style/cart.css">
  <title>Mon Panier</title>
  <%@include file="/style/boot.jsp"%>
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  <script src="/js/AddToCart.js"></script>
</head>
<header>
  <%@include file="/components/navbar.jsp"%>
</header>
<body>
<div class="container mt-4">
  <h2>Mon Panier</h2>

  <table class="table">
    <thead>
    <tr>
      <th>Produit</th>
      <th>Quantité</th>
      <th>Prix unitaire</th>
      <th>Total</th>
    </tr>
    </thead>
    <tbody>
    <%for (Cart cartItem : l_cart) { %>
    <tr>
      <td>
        <img src="<%= cartItem.getImage() %>" alt="<%= cartItem.getProduct_id() %>">
        <%= cartItem.getProduct_id() %>
      </td>
      <td><%= cartItem.getQuantity() %></td>
      <td><%= cartItem.getPrice() %></td>
      <td><%= cartItem.getQuantity() * cartItem.getPrice() %></td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <div>
    <a href="/utilisateur/commandes">Retour aux commandes</a>
  </div>
</div>

</body>
</html>
