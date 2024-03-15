<%@ page import="com.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/25/2023
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
Product product = (Product) session.getAttribute("product");
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <title>Produit - <%=product.getName()%></title>
    <%@include file="style/boot.jsp"%>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/js/AddToCart.js"></script>
</head>
<header>
    <%@include file="/components/navbar.jsp"%>
</header>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col-md-6">
            <!-- Image du produit -->
            <img src="<%= product.getImage() %>" alt="<%= product.getName() %>" class="img-fluid">
        </div>
        <div class="col-md-6">
            <!-- Détails du produit -->
            <div class="product-details">
                <h2><%= product.getName() %></h2>
                <p>Catégorie: <%= product.getCat() %></p>
                <p>Prix: <%= product.getPrice() %></p>

                <form class="addToCartForm" method="POST">
                    <input type="hidden" name="productId" value="<%= product.getId() %>"/>
                    <input type="hidden" name="quantity" value="1"/>
                    <button type="button" class="btn btn-primary" onclick="addToCart('add',<%= product.getId() %>, 1)">Ajouter au panier</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>