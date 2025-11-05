<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список препаратов</title></head>
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

<table border="1" cellpadding="6" bgcolor="white" align="center">
    <tr bgcolor="#b3daff">
        <th>ID</th><th>Торговое имя</th><th>ИНН</th><th>Дозировка</th><th>Форма</th><th>id поставщика</th><th></th>
    </tr>

    <c:forEach var="medicine" items="${medicines}">
        <tr bgcolor="#f0f8ff">
            <td><c:out value="${medicine.id}"/></td>
            <td><c:out value="${medicine.tradeName}"/></td>
            <td><c:out value="${medicine.inn}"/></td>
            <td><c:out value="${medicine.dosage}"/></td>
            <td><c:out value="${medicine.form}"/></td>
            <td><c:out value="${medicine.producerId}"/></td>
            <td>
                <a href="medicines?action=edit&id=${medicine.id}">
                    <button type="button" style="background-color: lightblue;">Редактировать</button>
                </a>
                <a href="medicines?action=delete&id=${medicine.id}" onclick="return confirm('Удалить аптеку id=${pharmacy.id}?');">
                    <button type="button" style="background-color: lightcoral;">Удалить</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
