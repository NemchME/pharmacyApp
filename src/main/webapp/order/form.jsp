<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Форма заказа</title></head>
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


<div align="center">
<h1>${order.id != null ? "Редактирование заказа" : "Добавление заказа"}</h1>
<form action="orders" method="post">
    <input type="hidden" name="id" value="${order.id}" />

    <table border="0" cellpadding="6">
        <tr>
                                    <td align="right"><b>Пользователь:</b></td>
                                    <td><select name="userId" required>
                                            <c:forEach var="user" items="${users}">
                                                <option value="${user.id}">
                                                    ${user.username}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
        </tr>
        <tr>
                                    <td align="right"><b>Препарат:</b></td>
                                    <td><select name="medicineId" required>
                                            <c:forEach var="medicine" items="${medicines}">
                                                <option value="${medicine.id}">
                                                    ${medicine.tradeName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
        </tr>
        <tr>
                                    <td align="right"><b>Аптека:</b></td>
                                    <td><select name="pharmacyId" required>
                                            <c:forEach var="pharmacy" items="${pharmacies}">
                                                <option value="${pharmacy.id}">
                                                    ${pharmacy.name} — ${producer.address}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
        </tr>
        <tr>
            <td align="right"><b>Количество:</b></td>
            <td><input type="number" name="quantity" value="${order.quantity}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>Статус:</b></td>
            <td><input type="text" name="status" value="${order.status}" size="40" required></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Сохранить">
            </td>
        </tr>
        </table>
</form>

<br>
<a href="orders">Вернуться к списку заказов</a>
</div>
</body>
</html>
