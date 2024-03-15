<%--
  Created by IntelliJ IDEA.
  User: alex2
  Date: 12/11/2023
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <title>Connection</title>
    <%@include file="style/boot.jsp"%>
    <style>

        .login-container {
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

        /* Style pour le bouton "Je ne suis pas encore inscrit" */
        a button {
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px; /* Espacement entre les boutons */
        }

        a button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<header>
    <%@include file="/components/navbar.jsp"%>
</header>

<div class="login-container">
    <c:if test="${not empty msg_failed}">
        <p class="text-center text-danger">${msg_failed}</p>
    </c:if>
    <c:if test="${not empty msg_success}">
        <p class="text-center text-success">${msg_success}</p>
    </c:if>
    <h2>Connexion</h2>
    <form action="/login" method="post">
        <input type="text" name="email" placeholder="email" required>
        <input type="password" name="pwd" placeholder="Mot de passe" required>
        <button type="submit">Se Connecter</button>
        <a href="/register">  <button type="button">Inscription</button></a>
    </form>
</div>

</body>
</html>
