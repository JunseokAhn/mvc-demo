<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Optional" %>
<%--
  Created by IntelliJ IDEA.
  User: guru
  Date: 2023-07-02
  Time: 오후 6:00
  To change this template use File | Settings | File Templates.
--%>
<%
    // name과 age 파라미터 가져오기
    String name = request.getParameter("name");
    String age = request.getParameter("age");

    // name과 age 값을 Optional로 감싸기
    Optional<String> optionalName = Optional.ofNullable(name);
    Optional<String> optionalAge = Optional.ofNullable(age);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member Info</title>
</head>
<body>
<h1>Member Info</h1>
    <% if (optionalName.isPresent() && optionalAge.isPresent()) { %>
        <p>Name: <%= optionalName.get() %></p>
        <p>Age: <%= optionalAge.get() %></p>
    <% } else { %>
        <% response.sendRedirect("index.html"); %>
    <% } %>
    <a href="index.html"> 돌아가기 />
</body>
</html>