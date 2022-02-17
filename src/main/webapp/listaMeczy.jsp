<%--
  Created by IntelliJ IDEA.
  User: Mateusz Rodak
  Date: 17.01.2022
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>


<html>
<head>
    <title>Lista Meczy</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <jsp:useBean id="obecnyCzas" class="java.util.Date" />
</head>
<body>

<br>
<a href="${context}/">Zmien konto</a>
<br>
<div align="center">
    <h1> ZAKLADY BUKMACHERSKIE</h1><br>
    <h1>Lista Meczy</h1>

    <table border="1" cellpadding="5">
        <caption><h2>Lista</h2></caption>
        <tr>
            <th>Data Meczu</th>
            <th>miasto</th>
            <th>druzyna1</th>
            <th>druzyna2</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="mecz" items="${listaMeczy}">
            <tr>
                <td><c:out value="${mecz.godzinaMeczu}"/></td>
                <td><c:out value="${mecz.miasto}"/></td>
                <td><c:out value="${mecz.druzyna1Obj.nazwaDruzyny}"/></td>
                <td><c:out value="${mecz.druzyna2Obj.nazwaDruzyny}"/></td>
                <td>
                    <a href="${context}/przegladZakladow?id=<c:out value='${mecz.id}'/>">Przeglad Zakladow</a>
                </td>
                <td>
                    <fmt:parseDate pattern="dd-MM-yyyy HH:mm" value="${mecz.godzinaMeczu}" var="data" />

                    <c:choose>
                        <c:when test="${obecnyCzas lt data}">
                            <a href="${context}/Zaklad?id=<c:out value='${mecz.id}'/>">Nowy Zaklad</a>
                        </c:when>
                        <c:otherwise>
                            MECZ ZAKONCZONY
                        </c:otherwise>
                    </c:choose>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
