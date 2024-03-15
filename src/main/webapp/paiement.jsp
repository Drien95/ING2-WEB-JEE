<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: alex2
  Date: 12/11/2023
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");
%>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <title>Paiement</title>
    <%@include file="style/boot.jsp"%>

    <style>
        .payment-container {
            max-width: 600px;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .payment-container h2 {
            color: #333;
            text-align: center;
        }

        form {
            display: grid;
            grid-gap: 10px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        button {
            background-color: #4caf50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<header>
    <%@include file="/components/navbar.jsp"%>
</header>


    <div class="payment-container">
        <c:if test="${not empty msg_success}">
            <p class="text-center text-success">${msg_success}</p>
        </c:if>
        <c:if test="${not empty msg_failed}">
            <p class="text-center text-danger">${msg_failed}</p>
        </c:if>

        <h2>Informations de Paiement</h2>
        <form action="/panier/validation" method="post">
            <input type="text" name="nom" placeholder="Nom sur la Carte" required>
            <input type="text" name="numerocarte" placeholder="Numéro de Carte" required>
            <input type="text" name="dateexpiration" placeholder="Date d'Expiration (MM/AA)" required>
            <input type="text" name="codecvv" placeholder="Code CVV" required>

            <!-- Champs bloqués avec les informations de l'utilisateur connecté -->
            <input type="text" name="nom_utilisateur" placeholder="Nom" value="<%= LOGIN_USER.getName() != null ? LOGIN_USER.getName():""%>" >
            <input type="text" name="adresse_utilisateur" placeholder="Adresse" value="<%= LOGIN_USER.getAdress() != null ? LOGIN_USER.getAdress():""%>" >
            <input type="text" name="ville_utilisateur" placeholder="Ville" value="<%= LOGIN_USER.getCity() != null ? LOGIN_USER.getCity():""%>" >
            <input type="text" name="codepostal_utilisateur" placeholder="Code Postal" value="<%= LOGIN_USER.getPostal() != 0 ? LOGIN_USER.getPostal():""%>" >

            <button type="submit">Valider le Paiement</button>
        </form>
    </div>

</body>
</html>
