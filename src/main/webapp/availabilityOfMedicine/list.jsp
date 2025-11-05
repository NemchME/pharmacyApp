<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список доступности препаратов</title></head>
<body bgcolor="#e6f2ff">
<body>
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
    <form action="availabilityOfMedicines" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="text" name="search" placeholder="Введите слово для поиска" value="${param.search}"/>

        <input type="submit" value="Применить"/>
    </form>

    <h2>Сортировка</h2>
    <form action="availabilityOfMedicines" method="get">
        <select name="sort">
                    <option value="">По ID</option>
                    <option value="pharmacyId" ${param.sort == 'pharmacyId' ? 'selected' : ''}>По id аптеки</option>
                    <option value="medicineId" ${param.sort == 'medicineId' ? 'selected' : ''}>По id препарата</option>
                    <option value="price" ${param.sort == 'price' ? 'selected' : ''}>По цене</option>
                    <option value="quantity" ${param.sort == 'quantity' ? 'selected' : ''}>По количеству</option>
                    <option value="updatedAt" ${param.sort == 'updatedAt' ? 'selected' : ''}>По времени обновления</option>
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

<h1>Список доступности препаратов</h1>
    <a href="availabilityOfMedicines?action=new">
        <button type="button" style="background-color: lightgreen; padding: 5px 10px;"><b>Добавить доступность препарата</b></button>
    </a>
</div>

<br/>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>ID аптеки</th><th>ID препарата</th><th>Цена</th><th>Количество</th><th>Время обновления</th>
    </tr>

    <c:forEach var="availabilityOfMedicine" items="${availabilityOfMedicines}">
        <tr>
            <td>${availabilityOfMedicine.id}</td>
            <td>${availabilityOfMedicine.pharmacyId}</td>
            <td>${availabilityOfMedicine.medicineId}</td>
            <td>${availabilityOfMedicine.price}</td>
            <td>${availabilityOfMedicine.quantity}</td>
            <td>${availabilityOfMedicine.updatedAt}</td>
            <td>
                <a href="availabilityOfMedicines?action=edit&id=${availabilityOfMedicine.id}">Редактировать</a> |
                <a href="availabilityOfMedicines?action=delete&id=${availabilityOfMedicine.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
