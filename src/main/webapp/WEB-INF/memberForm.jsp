<%--
  Created by IntelliJ IDEA.
  User: guru
  Date: 2023-07-02
  Time: 오후 6:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Member Form</title>
</head>
<body>
<h1>Member Form</h1>
<form action="/member/saveMember" method="get">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name" required>
  <br>
  <label for="age">Age:</label>
  <input type="number" id="age" name="age" required>
  <br>
  <input type="submit" value="Submit">
</form>
</body>
</html>
