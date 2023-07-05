<%--
  Created by IntelliJ IDEA.
  User: guru
  Date: 2023-07-02
  Time: 오후 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mvcdemo.entity.Member" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Members</title>
</head>
<body>
<h1>Members</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
    </tr>
    <% List<Member> memberList = (List<Member>) request.getAttribute("memberList"); %>
    <% try { %>
    <% for (Member member : memberList) { %>
    <tr>
        <td><%= member.getId() %></td>
        <td><%= member.getName() %></td>
        <td><%= member.getAge() %></td>
    </tr>
    <% } %>
    <% } catch (Exception e) { %>
    <tr>
        <td colspan="3">오류 발생</td>
    </tr>
    <% } %>
</table>
</body>
</html>
