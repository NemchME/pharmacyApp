<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Поиск препаратов по аптекам</title></head>
<body bgcolor="#e6f2ff">
<div align="center">
<c:if test="${availabilityList == null}">
<p>Не удалось найти данные о доступности препарата с id "${id}"</p>
</c:if>
<c:if test="${availabilityList != null}">
    <h2>Аптеки, где есть выбранный препарат:</h2>
    <table border="1" cellpadding="5">
        <tr bgcolor="#b3daff">
            <th>Аптека</th>
            <th>Количество</th>
            <th>Цена</th>
            <th>Как добраться от центра города</th>
        </tr>
        <c:forEach var="item" items="${availabilityList}">
            <tr bgcolor="#f0f8ff">
                <td>${item.pharmacyName}</td>
                <td>${item.quantity}</td>
                <td>${item.price}</td>
                <td>${item.wayFromCenter}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</body>
</html>
