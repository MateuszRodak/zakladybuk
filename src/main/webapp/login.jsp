<%--
  Created by IntelliJ IDEA.
  User: Mateusz Rodak
  Date: 17.01.2022
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
</head>
<body>

<center>
<h1> ZAKLADY BUKMACHERSKIE</h1><br>
    <h1>Rejestracja</h1>
<br>
    <td><c:out value="${wiadomoscZwrotna}"/></td><br><br>
<div>
<form action="login" method="post">
    PODAJ SWOJ PSEUDONIM: <input type="text" name="name" maxlength="15" required><br><br>
    PODAJ PIN SKLADAJACY SIE Z 4 CYFR:<input type="password" name="pin" minlength="4" maxlength="4" required pattern="[0-9]*" inputmode="numeric"><br><br>
    <input type="submit" value="START" >
</form>



</div>
</center>

</body>
</html>
