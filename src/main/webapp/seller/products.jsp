<%@ page import="com.DAO.ProductDAOimpl" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.Product" %>
<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/14/2023
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="style/style.css">
  <title>Admin - Tous les produits</title>
  <%@include file="../style/boot.jsp"%>
</head>
<header>
  <%@include file="/components/navbar.jsp"%>
</header>
<body>

<c:if test="${not empty msg_success}">
  <p class="text-center text-success">${msg_success}</p>
</c:if>
<c:if test="${not empty msg_failed}">
  <p class="text-center text-danger">${msg_failed}</p>
</c:if>

<table class="table table-striped">
  <thead class="bg-primary text-white">
  <tr>
    <th scope="col">ID</th>
    <th scope="col">Image</th>
    <th scope="col">Nom</th>
    <th scope="col">Catégorie</th>
    <th scope="col">Prix</th>
    <th scope="col">Quantité</th>
    <th scope="col">User</th>
    <th scope="col">Action</th>
  </tr>
  </thead>
  <tbody>
  <%
    ProductDAOimpl dao = new ProductDAOimpl(DBConnect.getConn());
    User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");
    List<Product> list_product = dao.getProductByUser(LOGIN_USER.getId());

    for(Product p : list_product){
  %>
  <tr>
    <th scope="row"><%=p.getId()%></th>
    <th scope="row"><img src="<%=p.getImage()%>"></th>
    <th scope="row"><%=p.getName()%></th>
    <th scope="row"><%=p.getCat()%></th>
    <th scope="row"><%=p.getPrice()%></th>
    <th scope="row"><%=p.getQuantity()%></th>
    <th scope="row"><%=p.getUser_id()%></th>
    <th><a href="/utilisateur/vendeur/modifier_produit?id=<%=p.getId()%>&u=<%=p.getUser_id()%>" class="btn btm-sm btn-primary">Modifier</a>
      <form action="/utilisateur/vendeur/produits" method="post">
        <input type="hidden" name="id" value="<%=p.getId()%>">
        <button type="submit" class="btn btn-sm btn-danger">Supprimer</button>
      </form>
    </th>
  </tr>
  <%

    }
  %>

  </tbody>
</table>

</body>
</html>
