<%--
  Created by IntelliJ IDEA.
  User: alex2
  Date: 12/11/2023
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="style/style.css">
  <title>Produit</title>
  <%@include file="style/boot.jsp"%>
</head>
<body>
<header>
  <%@include file="/components/navbar.jsp"%>
</header>

<div class="product-container">
  <div class="product-image">
    <img src="style/img/CHAT.jpg" alt="CHOKBAR">
  </div>
  <div class="product-details">
    <h2>CHOKBAR</h2>
    <p>Complétement CHOKBAR</p>
    <p>Prix : 19.99€ (PRIX CHOKBAR)</p>
    <button>Ajouter au Panier</button>
  </div>
</div>

</body>
</html>
