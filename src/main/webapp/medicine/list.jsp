<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список препаратов</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>Список препаратов</h1>

<a href="medicines?action=new">Добавить препарат</a>
<br><br>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>Торговое имя</th><th>ИНН</th><th>Дозировка</th><th>Форма</th><th>id поставщика</th>
    </tr>

    <c:forEach var="medicine" items="${medicines}">
        <tr>
            <td>${medicine.id}</td>
            <td>${medicine.tradeName}</td>
            <td>${medicine.inn}</td>
            <td>${medicine.dosage}</td>
            <td>${medicine.form}</td>
            <td>${medicine.producerId}</td>
            <td>
                <a href="medicines?action=edit&id=${medicine.id}">Редактировать</a> |
                <a href="medicines?action=delete&id=${medicine.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
