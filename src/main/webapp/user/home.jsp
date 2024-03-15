<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/26/2023
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User LOGIN_USER = (User) session.getAttribute("LOGIN_USER");
%>
<html>
<head>
    <title>Profil - <%=LOGIN_USER.getName()%></title>
    <%@include file="/style/boot.jsp"%>
</head>
<header>
    <%@include file="/components/navbar.jsp"%>
</header>
<body>
    <h2>Bienvenue, <%=LOGIN_USER.getName()%> !</h2>
    <h2>Vous avez actuellement <strong><%=LOGIN_USER.getFidelity_point()%></strong> points de fidelité !</h2>


    <div class="col-md-3">
        <a href="/utilisateur/infos">
            <div class="card">
                <div class="card-body text-center">
                    <h4>Infos persos</h4>
                </div>
            </div>
        </a>
    </div>

    <div class="col-md-3">
        <a href="/utilisateur/commandes">
            <div class="card">
                <div class="card-body text-center">
                    <h4>Mes commandes</h4>
                </div>
            </div>
        </a>
    </div>

    <div class="col-md-3">
        <a href="/panier">
            <div class="card">
                <div class="card-body text-center">
                    <h4>Mon panier</h4>
                </div>
            </div>
        </a>
    </div>

    <c:if test="${LOGIN_USER.getRole() eq 'seller'}">
        <div class="col-md-3">
            <a href="/utilisateur/vendeur/">
                <div class="card">
                    <div class="card-body text-center">
                        <h4>Vendeur</h4>
                    </div>
                </div>
            </a>
        </div>
    </c:if>

</body>
</html>
