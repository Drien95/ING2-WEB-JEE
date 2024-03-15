<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/26/2023
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.entity.User" %>
<%@ page import="com.entity.Cart" %>
<%@ page import="com.entity.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");
  List<Order> l_order = (List<Order>) session.getAttribute("l_order");
%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="../style/style.css">
  <title>Profil - <%=LOGIN_USER.getName()%></title>
  <%@include file="/style/boot.jsp"%>
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
      <th>Id</th>
      <th>Date-commande</th>
      <th>Moyen de paiement</th>
      <th>Total</th>
      <th>Details</th>
    </tr>
    </thead>
    <tbody>
    <% for (Order o : l_order) { %>
    <tr>
      <td>
        <p><%=o.getId()%></p>
      </td>
      <td>
        <p><%=o.getOrder_date()%></p>
      </td>
      <td><%=o.getMethod_payment()%></td>
      <td><%= o.getPrice()%></td>
      <td><a href="/utilisateur/commandes?id=<%=o.getId()%>">Voir détails</a></td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <div>
    <a href="/utilisateur">Retour</a>
  </div>
</div>
</body>
</html>