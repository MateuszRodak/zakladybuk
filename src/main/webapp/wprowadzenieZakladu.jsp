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
    <title>Wprowadzenie Zakladu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    <jsp:useBean id="obecnyCzas" class="java.util.Date" />
</head>
<body>

<br>
<a href="${context}/">Zmien konto</a>
<br>

<div align="center">
    <h1> ZAKLADY BUKMACHERSKIE</h1><br>
    <h1>Wprowadzenie Zakladu</h1>
    <a href="${context}/listaMeczy">Przeglad Meczow</a>
    <a href="${context}/przegladZakladow?id=<c:out value='${wybranyMecz.id}'/>">Przeglad Zakladow</a>



    <table border="2" cellpadding="5">
        <h2> Mecz: </h2>

        <tr>
            <th>Data</th>
            <th>miasto</th>
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
<tr>
    <th>Pseudonim</th>
    <th>druzyna1: GOLE</th>
    <th>druzyna2: GOLE</th>
    <th></th>
</tr>
<tr>
    <td><c:out value="${name}"/></td>
    <form action="SaveZaklad" method="post">
    <td><input type="number" name="bet1" value="0" maxlength="2" minlength="1" min="0" max="99" required pattern="[0-9]*" inputmode="numeric"/></td>
    <td><input type="number" name="bet2" value="0" maxlength="2" minlength="1" min="0" max="99" required pattern="[0-9]*" inputmode="numeric"/></td>
    <td>
        <input type="hidden" name="meczId" value="${wybranyMecz.id}"/>
        <input type="hidden" name="name" value="${name}"/>
        <input type="hidden" name="pin" value="${pin}"/>

        <fmt:parseDate pattern="dd-MM-yyyy HH:mm" value="${wybranyMecz.godzinaMeczu}" var="data" />

        <c:choose>
            <c:when test="${liczbaZakladow gt 2}">
                MAXMALNIE 3 ZAKLADY NA MECZ!
            </c:when>
            <c:when test="${obecnyCzas gt data}">
                MECZ ZAKONCZONY
            </c:when>
            <c:otherwise>
                <input type="submit" value="Zapisz"/>
            </c:otherwise>
        </c:choose>

    </td>
    </form>
</tr>
</table>
    <c:if test="${BET}">ZAKLAD JUZ OBSTAWIONY</c:if>
</div>


</body>
</html>
