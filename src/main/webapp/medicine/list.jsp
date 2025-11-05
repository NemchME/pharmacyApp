<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список препаратов</title></head>
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
<h1>Список препаратов</h1>
<div align="right">
    <h2>Поиск по слову</h2>
    <form action="medicines" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="text" name="search" placeholder="Введите слово для поиска" value="${param.search}"/>

        <input type="submit" value="Применить"/>
    </form>

    <h2>Сортировка</h2>
    <form action="medicines" method="get">
        <select name="sort">
                    <option value="">По ID</option>
                    <option value="tradeName" ${param.sort == 'tradeName' ? 'selected' : ''}>По торговому имени</option>
                    <option value="inn" ${param.sort == 'inn' ? 'selected' : ''}>По ИНН</option>
                    <option value="dosage" ${param.sort == 'dosage' ? 'selected' : ''}>По дозировке</option>
                    <option value="form" ${param.sort == 'form' ? 'selected' : ''}>По форме</option>
                    <option value="producerId" ${param.sort == 'producerId' ? 'selected' : ''}>По id производителя</option>
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

<h1>Список препаратов</h1>
    <a href="medicines?action=new">
        <button type="button" style="background-color: lightgreen; padding: 5px 10px;"><b>Добавить препарат</b></button>
    </a>
</div>

<br/>

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
