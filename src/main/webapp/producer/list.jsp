<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список производителей</title></head>
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
    <form action="producers" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="text" name="search" placeholder="Введите слово для поиска" value="${param.search}"/>

        <input type="submit" value="Применить"/>
    </form>

    <h2>Сортировка</h2>
    <form action="producers" method="get">
        <select name="sort">
                    <option value="">По ID</option>
                    <option value="name" ${param.sort == 'name' ? 'selected' : ''}>По названию</option>
                    <option value="country" ${param.sort == 'country' ? 'selected' : ''}>По стране</option>
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

<h1>Список производителей</h1>
    <a href="producers?action=new">
        <button type="button" style="background-color: lightgreen; padding: 5px 10px;"><b>Добавить производителя</b></button>
    </a>
</div>

<br/>

<table border="1" cellpadding="6" bgcolor="white" align="center">
    <tr bgcolor="#b3daff">
        <th>ID</th><th>Название</th><th>Страна</th><th></th>
    </tr>

    <c:forEach var="producer" items="${producers}">
        <tr bgcolor="#f0f8ff">
            <td><c:out value="${producer.id}"/></td>
            <td><c:out value="${producer.name}"/></td>
            <td><c:out value="${producer.country}"/></td>

            <td align="center">
                <a href="producers?action=edit&id=${producer.id}">
                    <button type="button" style="background-color: lightblue;">Редактировать</button>
                </a>

                <a href="producers?action=delete&id=${producer.id}" onclick="return confirm('Удалить производителя id=${producer.id}?');">
                    <button type="button" style="background-color: lightcoral;">Удалить</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
