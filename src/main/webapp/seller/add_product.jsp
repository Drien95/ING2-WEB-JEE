<%@ page import="com.DAO.ProductDAOimpl" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="com.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/14/2023
  Time: 1:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    ProductDAOimpl dao_nav = new ProductDAOimpl(DBConnect.getConn());
//    List<String> list_cat = dao_nav.getAllCat();
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <title>Vendeur - Ajouter produit</title>
    <%@include file="../style/boot.jsp"%>
    <style>
        input,
        select,
        button {
            display: block;
            width: 100%;
            margin-bottom: 15px;
            padding: 10px;
            box-sizing: border-box;
        }

        select {
            height: 40px;
        }

        #categorie {
            display: none;
        }

        button {
            background-color: #333;
            color: #fff;
            cursor: pointer;
        }

        button:hover {
            background-color: #555;
        }

        .text-center {
            text-align: center;
        }

        .text-success {
            color: #4caf50;
        }

        .text-danger {
            color: #f44336;
        }
    </style>
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


<form action="/utilisateur/vendeur/ajouter_produit" method="post">

    <input type="text" name="pName" placeholder="Nom du produit" required>

    <select id="inputState" name="pers" onchange="checkCat()">
        <option value="New">Nouvelle Catégorie</option>
        <option selected>--Catégorie--</option>
        <%
            for(String c : list_cat){
        %>
        <option value="<%=c%>"><%=c%></option>
        <%
            }
        %>
    </select>
    <input type="text" id="entreePersonnalisee" name="cat" style="display: none;" placeholder="Entrez votre choix personnalisé">

    <input type="number" step="any" min="0" name="price" placeholder="Prix" required>

    <input type="number" min="1" name="quantity" placeholder="Quantité" required>

    <input type="text" name="image" placeholder="image" required>

    <button type="submit">Ajouter</button>
</form>
<script>
    function checkCat() {
        var inputState = document.getElementById("inputState");
        var entreePersonnalisee = document.getElementById("entreePersonnalisee");

        // Vérifier si l'option sélectionnée est "New"
        if (inputState.value === "New") {
            // Afficher le champ d'entrée personnalisée
            entreePersonnalisee.style.display = "block";
        } else {
            // Cacher le champ d'entrée personnalisée
            entreePersonnalisee.style.display = "none";
        }
    }
</script>

</body>
</html>
