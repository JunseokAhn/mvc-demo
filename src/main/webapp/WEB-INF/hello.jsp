<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guru
  Date: 2023-07-01
  Time: 오후 5:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    Hello
    <% if (request.getAttribute("name") == null) {
        out.print("World");
    } else {
        out.print(request.getAttribute("name"));
    } %>

    <h2>These are request list</h2>

    <c:forEach var="item" items="${requestScope}">
        ${item.key} : ${item.value}
    </c:forEach>

    <%
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
    %>
    <p><b><%= paramName %></b>: <%= paramValue %></p>
    <%
        }
    %>
</body>
</html>
