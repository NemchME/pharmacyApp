<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список пользователей</title></head>
<body bgcolor="#e6f2ff">
<div align="center" style="margin-bottom: 20px;">
    <table border="0" cellpadding="8" bgcolor="#cce5ff">
        <tr>
            <td><a href="stats"><b>Статистика</b></a></td>
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
    <form action="users" method="get">
        <input type="hidden" name="action" value="list"/>
        <input type="text" name="search" placeholder="Введите слово для поиска" value="${param.search}" required/>

        <input type="submit" value="Применить"/>
    </form>

    <h2>Сортировка</h2>
    <form action="users" method="get">
        <select name="sort">
                    <option value="">По ID</option>
                    <option value="nickname" ${param.sort == 'nickname' ? 'selected' : ''}>По никнейму</option>
                    <option value="phone" ${param.sort == 'phone' ? 'selected' : ''}>По телефону</option>
                    <option value="email" ${param.sort == 'email' ? 'selected' : ''}>По эл. почте</option>
                    <option value="role" ${param.sort == 'role' ? 'selected' : ''}>По роли</option>
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

<h1>Список пользователей</h1>
    <a href="users?action=new">
        <button type="button" style="background-color: lightgreen; padding: 5px 10px;"><b>Добавить пользователя</b></button>
    </a>
</div>

<br/>

<div align="center">
    <c:if test="${search != null}">
        <div>
            <h2>Результат поиска по слову: "${search}"</h2>
        </div>
    </c:if>
</div>
<table border="1" cellpadding="6" bgcolor="white" align="center">
    <tr bgcolor="#b3daff">
        <th>ID</th><th>Никнейм</th><th>Эл. почта</th><th>Роль</th><th></th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr bgcolor="#f0f8ff">
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.role}"/></td>
            
            <td align="center">
                <a href="orderInfo?userId=${user.id}">
                    <button type="button" style="background-color: lightgreen;">Заказы</button>
                </a>
                <a href="users?action=edit&id=${user.id}">
                    <button type="button" style="background-color: lightblue;">Редактировать</button>
                </a>
                <a href="users?action=delete&id=${user.id}" onclick="return confirm('Удалить пользователя id=${user.id}?');">
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
                <a href="users?page=${currentPage - 1}&size=${currentSize}">&laquo; Предыдущая</a>
            </c:if>

            Страница ${currentPage} из ${totalPages}

            <c:if test="${currentPage < totalPages}">
                <a href="users?page=${currentPage + 1}&size=${currentSize}">Следующая &raquo;</a>
            </c:if>
        </div>
    </c:if>
</div>
</body>
</html>
