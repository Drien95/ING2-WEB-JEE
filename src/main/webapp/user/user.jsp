<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/26/2023
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");
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
  <h2>Profil de <%=LOGIN_USER.getName()%></h2>

  <c:if test="${not empty msg_success}">
    <p class="text-center text-success">${msg_success}</p>
  </c:if>
  <c:if test="${not empty msg_failed}">
    <p class="text-center text-danger">${msg_failed}</p>
  </c:if>

  <form action="/utilisateur/infos" method="post">
    <input type="hidden" name="formName" value="form_infos">
    <input type="hidden" name="id" value="<%=LOGIN_USER.getId()%>">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" value="<%=LOGIN_USER.getName()%>">

    <label for="adresse">Adresse :</label>
    <input type="text" id="adresse" name="adresse" value="<%=LOGIN_USER.getAdress()%>">

    <label for="ville">Ville :</label>
    <input type="text" id="ville" name="ville" value="<%=LOGIN_USER.getCity()%>">

    <label for="codePostal">Code Postal :</label>
    <input type="text" id="codePostal" name="codePostal" value="<%=LOGIN_USER.getPostal()%>">

    <button type="submit" class="btn btn-primary">Modifier</button>
  </form>

  <form action="/utilisateur/infos" method="post">
    <input type="hidden" name="formName" value="form_mail">
    <input type="hidden" name="id" value="<%=LOGIN_USER.getId()%>">

    <label for="codePostal">Mail :</label>
    <input type="text" id="mail" name="mail" value="<%=LOGIN_USER.getEmail()%>">

    <button type="submit" class="btn btn-sm btn-danger">mofidifer</button>
  </form>

  <form action="/utilisateur/infos" method="post">
    <input type="hidden" name="formName" value="form_pwd">
    <input type="hidden" name="id" value="<%=LOGIN_USER.getId()%>">

    <label for="codePostal">Mot de passe :</label>
    <input type="password" id="pwd" name="pwd">
    <input type="password" id="pwd_verif" name="pwd_verif">

    <button type="submit" class="btn btn-sm btn-danger">modifier</button>
  </form>


</div>
</body>
</html>
