<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Поиск препаратов по аптекам</title></head>
<body bgcolor="#e6f2ff">
<div align="center">
<c:if test="${pharmacyInfoList == null}">
<p>Не удалось найти данные о препаратах у аптеки с id="${pharmacyId}"</p>
</c:if>
<c:if test="${pharmacyInfoList != null}">
    <h2>Заказы у пользователя с id="${pharmacyId}"</h2>
    <table border="1" cellpadding="5">
        <tr bgcolor="#b3daff">
            <th>Название препарата</th>
            <th>Цена</th>
            <th>Доступное количество</th>
            <th>Время обновления</th>
        </tr>
        <c:forEach var="item" items="${pharmacyInfoList}">
            <tr bgcolor="#f0f8ff">
                <td>${item.medicineName}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td>${item.updatedAt}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="pharmacies?page=1&size=5">Вернуться к списку аптек</a>
</div>
</body>
</html>
