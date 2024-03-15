<%--
  Created by IntelliJ IDEA.
  User: alex2
  Date: 09/11/2023
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <title>Page d'accueil</title>
    <%@include file="style/boot.jsp"%>
</head>
<body>
<header>
    <%@include file="/components/navbar.jsp"%>
</header>

<c:if test="${not empty msg_success}">
    <p class="text-center text-success">${msg_success}</p>
</c:if>
<c:if test="${not empty msg_failed}">
    <p class="text-center text-danger">${msg_failed}</p>
</c:if>
<div class="accueil_style">
    <div class="h1">
        <p>Nos produits les plus appréciés </p>
    </div>
    <div class="conteneur_img_accueil">
        <div class="img_accueil">
           <a href="page_produit.jsp"> <img src="style/img/CHAT.jpg" alt=""> </a>
        </div>
        <div class="img_accueil">
            <img src="style/img/CHAT.jpg" alt="">
        </div>
        <div class="img_accueil">
            <img src="style/img/CHAT.jpg" alt="">
        </div>
        <div class="img_accueil">
            <img src="style/img/CHAT.jpg" alt="">
        </div>
    </div>
</div>

<footer class="footer-basic">
    <p>Retour à l'accueil</p>
</footer>
</body>
</html>
