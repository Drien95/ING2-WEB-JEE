<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/14/2023
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="style/style.css">
  <title>Vendeur</title>
  <%@include file="../style/boot.jsp"%>
</head>
<header>
  <%@include file="/components/navbar.jsp"%>
</header>
<body>
<p>Home Seller Page</p>

<div class="col-md-3">
  <a href="/utilisateur/vendeur/produits">
    <div class="card">
      <div class="card-body text-center">
        <h4>Mes produits</h4>
      </div>
    </div>
  </a>
</div>

<div class="col-md-3">
  <a href="/utilisateur/vendeur/ajouter_produit">
    <div class="card">
      <div class="card-body text-center">
        <h4>Ajouter un produit</h4>
      </div>
    </div>
  </a>
</div>

</body>
</html>
