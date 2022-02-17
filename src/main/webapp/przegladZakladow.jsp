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
    <title>Przeglad Zakladow</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    <jsp:useBean id="obecnyCzas" class="java.util.Date" />

</head>
<body>


<br>
<a href="${context}/">Zmien konto</a>
<br>

<div align="center">
    <h1> ZAKLADY BUKMACHERSKIE</h1><br>
    <h1>Przeglad Zakladow</h1>
    <a href="${context}/listaMeczy">Lista Meczy</a>
    <table border="2" cellpadding="5">
        <h2> Mecz: </h2>

            <tr>
                <th>Data</th>
                <th>Miasto</th>
                <th>Druzyna1</th>
                <th>Druzyna2</th>
            </tr>
            <tr>
                <td><c:out value="${wybranyMecz.godzinaMeczu}"/></td>
                <td><c:out value="${wybranyMecz.miasto}"/></td>
                <td><c:out value="${wybranyMecz.druzyna1Obj.nazwaDruzyny}"/></td>
                <td><c:out value="${wybranyMecz.druzyna2Obj.nazwaDruzyny}"/></td>
            </tr>

    </table>


    <table border="1" cellpadding="5">
        <caption><h2>Lista zakladow</h2></caption>

        <c:out value="${miasto}"/>
    <br>
        <fmt:parseDate pattern="dd-MM-yyyy HH:mm" value="${wybranyMecz.godzinaMeczu}" var="data" />

        <c:choose>
            <c:when test="${liczbaZakladow gt 2}">
                MAXMALNIE 3 ZAKLADY NA MECZ!
            </c:when>
            <c:when test="${obecnyCzas gt data}">
                MECZ ZAKONCZONY
            </c:when>
            <c:otherwise>
                <a href="${context}/Zaklad?id=<c:out value='${wybranyMecz.id}'/>">Nowy Zaklad</a>
            </c:otherwise>
        </c:choose>


        <tr>
            <th>data zakladu</th>
            <th>nick</th>
            <th>BET</th>
        </tr>
        <c:forEach var="zaklad" items="${listaZakladow}">
            <tr>
                <td><c:out value="${zaklad.dataZakladu}"/></td>
                <td>
                    <c:if test="${zaklad.uzytkownikName == uzytkownikSesji && zaklad.uzytkownikPin == pinSesji}">
                        <p style="color:green">
                    </c:if>
                    <c:out value="${zaklad.uzytkownikName}"/>
                        </p>
                </td>
                <td><c:out value="${zaklad.druzyna1Gole}"/> : <c:out value="${zaklad.druzyna2Gole}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
