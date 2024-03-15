<%@ page import="com.DAO.ProductDAOimpl" %>
<%@ page import="com.DB.DBConnect" %>
<%@ page import="com.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/14/2023
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Product product = (Product) request.getAttribute("product");
//  ProductDAOimpl dao_nav = new ProductDAOimpl(DBConnect.getConn());
//  List<String> list_cat = dao_nav.getAllCat();
%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="style/style.css">
  <title>Vendeur - Modifier Produit</title>
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


<form action="/utilisateur/vendeur/modifier_produit?id=<%=product.getId()%>&u=<%=product.getUser_id()%>" method="post">
  <input type="hidden" name="id" value="<%=product.getId()%>">
  <input type="text" name="pName" value="<%=product.getName()%>" placeholder="Nom du produit" required>

  <select id="inputState" name="pers" onchange="checkCat()">
    <option selected value="<%=product.getCat()%>"><%=product.getCat()%></option>
    <option value="New">Nouvelle Catégorie</option>
    <%
      for(String c : list_cat){
    %>
    <option value="<%=c%>"><%=c%></option>
    <%
      }
    %>
  </select>
  <input type="text" id="entreePersonnalisee" name="cat" style="display: none;" placeholder="Entrez votre choix personnalisé">

  <input type="number" value="<%=product.getPrice()%>" step="any" min="0" name="price" placeholder="Prix" required>

  <input type="number" value="<%=product.getQuantity()%>" min="1" name="quantity" placeholder="Quantité" required>

  <input type="text" value="<%=product.getImage()%>" name="image" placeholder="image" required>

  <button type="submit">Modifier</button>
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
