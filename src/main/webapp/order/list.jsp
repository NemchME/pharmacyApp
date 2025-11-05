<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список заказов</title></head>
<body bgcolor="#e6f2ff">
<div align="center" style="margin-bottom: 20px;">
    <table border="0" cellpadding="8" bgcolor="#cce5ff">
        <tr>
            <td><a href="availabilityOfMedicines"><b>AvailabilityOfMedicines</b></a></td>
            <td><a href="medicines"><b>Medicine</b></a></td>
            <td><a href="orders"><b>Order</b></a></td>
            <td><a href="pharmacies"><b>Pharmacy</b></a></td>
            <td><a href="producers"><b>Producer</b></a></td>
            <td><a href="users"><b>User</b></a></td>
        </tr>
    </table>
</div>
<div align="right">
    <h2>Поиск по слову</h2>
    <form action="orders" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="text" name="search" placeholder="Введите слово для поиска" value="${param.search}"/>

        <input type="submit" value="Применить"/>
    </form>

    <h2>Сортировка</h2>
    <form action="orders" method="get">
        <select name="sort">
                    <option value="">По ID</option>
                    <option value="userId" ${param.sort == 'userId' ? 'selected' : ''}>По id пользователя</option>
                    <option value="medicineId" ${param.sort == 'medicineId' ? 'selected' : ''}>По id лекарства</option>
                    <option value="pharmacyId" ${param.sort == 'pharmacyId' ? 'selected' : ''}>По id аптеки</option>
                    <option value="quantity" ${param.sort == 'quantity' ? 'selected' : ''}>По количеству</option>
                    <option value="status" ${param.sort == 'status' ? 'selected' : ''}>По статусу</option>
                    <option value="createdAt" ${param.sort == 'createdAt' ? 'selected' : ''}>По времени создания</option>
        </select>
        <select name="comparator">
            <option value="asc" ${param.comparator == 'asc' ? 'selected' : ''}>По возрастанию</option>
            <option value="desc" ${param.comparator == 'desc' ? 'selected' : ''}>По убыванию</option>
        </select>
        <input type="submit" value="Применить"/>
    </form
</div>
    <br/>
<div align="center">

<h1>Список заказов</h1>
    <a href="orders?action=new">
        <button type="button" style="background-color: lightgreen; padding: 5px 10px;"><b>Добавить заказ</b></button>
    </a>
</div>

<br/>
<table border="1" cellpadding="6" bgcolor="white" align="center">
    <tr bgcolor="#b3daff">
        <th>ID</th><th>ID пользователя</th><th>ID лекарства</th><th>ID аптеки</th><th>Количество</th><th>Статус</th><th>Время создания</th><th></th>
    </tr>

    <c:forEach var="order" items="${orders}">
        <tr bgcolor="#f0f8ff">
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.userId}"/></td>
            <td><c:out value="${order.medicineId}"/></td>
            <td><c:out value="${order.pharmacyId}"/></td>
            <td><c:out value="${order.quantity}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td><c:out value="${order.createdAt}"/></td>

            <td align="center">
                <a href="orders?action=edit&id=${order.id}">
                    <button type="button" style="background-color: lightblue;">Редактировать</button>
                </a>
                <a href="orders?action=delete&id=${order.id}" onclick="return confirm('Удалить заказ id=${order.id}?');">
                    <button type="button" style="background-color: lightcoral;">Удалить</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
