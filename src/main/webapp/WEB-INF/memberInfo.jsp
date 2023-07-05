<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.example.mvcdemo.entity.Member" %>
<%--
  Created by IntelliJ IDEA.
  User: guru
  Date: 2023-07-02
  Time: 오후 6:00
  To change this template use File | Settings | File Templates.
--%>
<%
    // member 가져오기
    Member member= (Member) request.getAttribute("member");
    // name과 age 값을 Optional로 감싸기
    Optional<String> optionalName = Optional.ofNullable(member.getName());
    Optional<Integer> optionalAge = Optional.ofNullable(member.getAge());
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
        <% response.sendRedirect("/index.html"); %>
    <% } %>
    <a href="/index.html"> 돌아가기
</body>
</html>