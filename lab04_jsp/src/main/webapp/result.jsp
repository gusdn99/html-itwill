<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
</head>
<body>
    <%@ include file="header.jspf" %>
    
    <main>
        <h1>RESULT</h1>
        <h2>안녕하세요, ${ param.username }!</h2>
        <%-- EL에서 request.getParameter("username")을 대신하는 방법 --%>
    </main>
</body>
</html>