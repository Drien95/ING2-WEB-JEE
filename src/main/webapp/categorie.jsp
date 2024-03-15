<%@ page import="com.entity.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/25/2023
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> list_product = (List<Product>) session.getAttribute("list_product");
    String cat = (String) session.getAttribute("cat");
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <title>Categorie - <%=cat%></title>
    <%@include file="/style/boot.jsp"%>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="/js/AddToCart.js"></script>
</head>
<header>
    <%@include file="/components/navbar.jsp"%>
</header>
<body>

<div class="container mt-4">
    <div class="row">

        <%
            for(Product product : list_product){
        %>
        <div class="col-md-3 mb-4">
            <div class="card <%= product.getQuantity() < 1 ? "disabled-card" : "" %>">
                <form class="addToCartForm" action="/panier" method="POST">
                    <input type="hidden" name="productId" value="<%= product.getId() %>"/>
                    <input type="hidden" name="quantity" value="1"/>
                    <a href="/produit?id=<%=product.getId()%>">
                        <img src="<%= product.getImage() %>" class="card-img-top product-image" alt="<%= product.getName() %>">
                    </a>
                        <div class="card-body">
                            <a href="/produit?id=<%=product.getId()%>">
                                <h5 class="card-title"><%= product.getName() %></h5>
                            </a>
                            <p class="card-text">Price: <%= product.getPrice() %> €</p>
                            <button type="button" class="btn btn-primary addToCartBtn" onclick="addToCart('add',<%= product.getId() %>, 1)">Ajouter au panier</button>
                        </div>

                </form>
            </div>
        </div>
        <% } %>

    </div>
</div>

</body>
</html>