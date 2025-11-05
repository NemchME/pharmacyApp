<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Список аптек</title>
</head>
<body bgcolor="#e6f2ff">

<div align="center" style="margin-bottom: 20px;">
    <table border="0" cellpadding="8" bgcolor="#cce5ff">
        <tr>
            <td><a href="availabilityOfMedicines?page=1&size=5"><b>AvailabilityOfMedicines</b></a></td>
            <td><a href="medicines?page=1&size=5"><b>Medicine</b></a></td>
            <td><a href="orders?page=1&size=5"><b>Order</b></a></td>
            <td><a href="pharmacies?page=1&size=5"><b>Pharmacy</b></a></td>
            <td><a href="producers?page=1&size=5"><b>Producer</b></a></td>
            <td><a href="users?page=1&size=5"><b>User</b></a></td>
        </tr>
    </table>
</div>

<div align="right">
    <h2>Поиск по слову</h2>
    <form action="pharmacies" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="text" name="search" placeholder="Введите слово для поиска" value="${param.search}"/>

        <input type="submit" value="Применить"/>
    </form>

    <h2>Сортировка</h2>
    <form action="pharmacies" method="get">
        <select name="sort">
                    <option value="">По ID</option>
                    <option value="name" ${param.sort == 'name' ? 'selected' : ''}>По названию</option>
                    <option value="address" ${param.sort == 'address' ? 'selected' : ''}>По адресу</option>
                    <option value="phone" ${param.sort == 'phone' ? 'selected' : ''}>По телефону</option>
                    <option value="workingHours" ${param.sort == 'workingHours' ? 'selected' : ''}>По режиму работы</option>
                    <option value="wayFromCenter" ${param.sort == 'wayFromCenter' ? 'selected' : ''}>По пути от центра</option>
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

<h1>Список аптек</h1>
    <a href="pharmacies?action=new">
        <button type="button" style="background-color: lightgreen; padding: 5px 10px;"><b>Добавить аптеку</b></button>
    </a>
</div>

<br/>

<table border="1" cellpadding="6" bgcolor="white" align="center">
    <tr bgcolor="#b3daff">
        <th>ID</th>
        <th>Название</th>
        <th>Адрес</th>
        <th>Телефон</th>
        <th>Режим работы</th>
        <th>Путь от центра</th>
        <th></th>
    </tr>

    <c:forEach var="pharmacy" items="${pharmacies}">
        <tr bgcolor="#f0f8ff">
            <td><c:out value="${pharmacy.id}"/></td>
            <td><c:out value="${pharmacy.name}"/></td>
            <td><c:out value="${pharmacy.address}"/></td>
            <td><c:out value="${pharmacy.phone}"/></td>
            <td><c:out value="${pharmacy.workingHours}"/></td>
            <td><c:out value="${pharmacy.wayFromCenter}"/></td>
            <td align="center">
                <a href="pharmacies?action=edit&id=${pharmacy.id}">
                    <button type="button" style="background-color: lightblue;">Редактировать</button>
                </a>

                <a href="pharmacies?action=delete&id=${pharmacy.id}" onclick="return confirm('Удалить аптеку id=${pharmacy.id}?');">
                    <button type="button" style="background-color: lightcoral;">Удалить</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<div align="center">
    <c:if test="${totalPages > 1}">
        <div>
            <c:if test="${currentPage > 1}">
                <a href="pharmacies?page=${currentPage - 1}&size=${currentSize}">&laquo; Предыдущая</a>
            </c:if>

            Страница ${currentPage} из ${totalPages}

            <c:if test="${currentPage < totalPages}">
                <a href="pharmacies?page=${currentPage + 1}&size=${currentSize}">Следующая &raquo;</a>
            </c:if>
        </div>
    </c:if>
</div>
</body>
</html>
