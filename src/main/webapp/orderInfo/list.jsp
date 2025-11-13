<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Поиск заказов по пользователям</title></head>
<body bgcolor="#e6f2ff">
<div align="center">
<c:if test="${orderInfoList == null}">
<p>Не удалось найти данные о заказах у пользователя с id="${userId}"</p>
</c:if>
<c:if test="${orderInfoList != null}">
    <h2>Заказы у пользователя с id="${userId}"</h2>
    <table border="1" cellpadding="5">
        <tr bgcolor="#b3daff">
            <th>Название препарата</th>
            <th>Название аптеки</th>
            <th>Количество</th>
            <th>Статус</th>
            <th>Время создания</th>
        </tr>
        <c:forEach var="item" items="${orderInfoList}">
            <tr bgcolor="#f0f8ff">
                <td>${item.medicineName}</td>
                <td>${item.pharmacyName}</td>
                <td>${item.quantity}</td>
                <td>${item.status}</td>
                <td>${item.createdAt}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="users?page=1&size=5">Вернуться к списку пользователей</a>
</div>
</body>
</html>
