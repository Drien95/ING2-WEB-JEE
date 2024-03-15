<%@ page import="com.DB.DBConnect" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%

    String test;
    try {


    java.sql.Connection conn = DBConnect.getConn();

    test = "ok";

}catch(Exception e){
    System.out.println("ezezez");
    test = "nok";

}

%>
<%= test%>

</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>