<%--
  Created by IntelliJ IDEA.
  User: alex2
  Date: 12/11/2023
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="style/style.css">
  <title>Inscription</title>
  <%@include file="style/boot.jsp"%>
  <style>
    .registration-container {
      max-width: 400px;
      margin: 20px auto;
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
      color: #343a40;
      text-align: center;
    }

    form {
      display: grid;
      grid-gap: 15px;
    }

    input {
      width: 100%;
      padding: 10px;
      border: 1px solid #ced4da;
      border-radius: 4px;
      box-sizing: border-box;
    }

    button {
      background-color: #007bff;
      color: white;
      padding: 10px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      width: 100%;
    }

    button:hover {
      background-color: #0056b3;
    }

    .text-center {
      text-align: center;
    }

    .text-success {
      color: #28a745;
    }

    .text-danger {
      color: #dc3545;
    }
  </style>
</head>
<body>
<header>
  <%@include file="/components/navbar.jsp"%>
</header>
<div class="registration-container">
  <h2>Inscription</h2>
  <c:if test="${not empty msg_success}">
    <p class="text-center text-success">${msg_success}</p>
  </c:if>
  <c:if test="${not empty msg_failed}">
    <p class="text-center text-danger">${msg_failed}</p>
  </c:if>

  <form action="/register" method="post">
    <input type="text" name="fname" placeholder="Nom" required>
    <input type="email" name="email" placeholder="Adresse Email" required>
    <input type="password" name="pwd" placeholder="Mot de Passe" required>
    <button type="submit">Valider votre inscription</button>
  </form>
</div>

</body>
</html>




