<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
helloindex.jsp<br>
<%--想查看jsp转成Java文件，可以在tomcat的work目录中查看--%>
<%
    //https://www.cnblogs.com/chaplu/p/16030775.html
    pageContext.setAttribute("page", "page值");
%>

<%= pageContext.getAttribute("page") %><br>
<%= request.getAttribute("request1") %><br>
<%= request.getAttribute("request2") %><br>
<%= session.getAttribute("session") %><br>
<%= application.getAttribute("application") %><br>

<c:out value="${page}" /><br>
<c:out value="${request1}" /><br>
<c:out value="${request2}" /><br>
<c:out value="${session}" /><br>
<c:out value="${application}" /><br>


</body>
</html>