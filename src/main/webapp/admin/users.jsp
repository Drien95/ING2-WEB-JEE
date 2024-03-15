<%@ page import="com.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/21/2023
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<User> list_user = (List<User>) request.getAttribute("list_user");
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <title>Admin - Utilisateurs</title>
    <%@include file="../style/boot.jsp"%>
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
<table class="table table-striped">
    <thead class="bg-primary text-white">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Nom</th>
        <th scope="col">Email</th>
        <th scope="col">Role</th>
        <th scope="col">Delete</th>
        <th scope="col">Edit Role</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(User user : list_user){
    %>
    <tr>
        <th scope="row"><%=user.getId()%></th>
        <th scope="row"><%=user.getName()%></th>
        <th scope="row"><%=user.getEmail()%></th>
        <th scope="row"><%=user.getRole()%></th>
        <th>
            <form action="/admin/utilisateurs" method="post">
                <input type="hidden" name="formName" value="form_del">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <button type="submit" class="btn btn-sm btn-danger">Supprimer</button>
            </form>
        </th>
        <th>
            <form action="/admin/utilisateurs" method="post">
                <input type="hidden" name="formName" value="form_role">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <select id="inputState" name="role">
                    <option selected value="<%=user.getRole()%>"><%=user.getRole()%></option>
                    <option value="admin">Administrateur</option>
                    <option value="seller">Vendeur</option>
                    <option value="random">User</option>
                </select>
                <button type="submit" class="btn btn-sm btn-danger">Valider</button>
            </form>
        </th>

    </tr>
    <%

        }
    %>

    </tbody>
</table>
</body>
</html>
