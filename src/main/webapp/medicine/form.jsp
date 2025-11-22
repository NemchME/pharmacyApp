<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Форма препарата</title></head>
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
<h1>${medicine.id != null ? "Редактирование препарата" : "Добавление препарата"}</h1>
<form action="medicines" method="post">
    <input type="hidden" name="id" value="${medicine.id}" />

        <table border="0" cellpadding="6">
            <tr>
                <td align="right"><b>Торговое имя:</b></td>
                <td><input type="text" name="tradeName" value="${medicine.tradeName}" size="40" required></td>
            </tr>
            <tr>
                <td align="right"><b>ИНН:</b></td>
                <td>
                    <input type="text" name="inn" value="${medicine.inn}" size="40" required><br>

                    <c:if test="${not empty innError}">
                        <span style="color: red; font-size: 12px;">
                            ${innError}
                        </span>
                    </c:if>
                </td>
            </tr>
                <td align="right"><b>Дозировка:</b></td>
                <td><input type="text" name="dosage" value="${medicine.dosage}" size="40" required></td>
            </tr>
            <tr>
                <td align="right"><b>Форма:</b></td>
                <td><input type="text" name="form" value="${medicine.form}" size="40" required></td>
            </tr>
            <tr>
                <td align="right"><b>Поставщик:</b></td>
                <td><select name="producerId" required>
                        <c:forEach var="producer" items="${producers}">
                            <option value="${producer.id}">
                                ${producer.name} — ${producer.country}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Сохранить">
                </td>
            </tr>
            </table>
</form>

<br>
<a href="medicines">Вернуться к списку препаратов</a>
</div>
</body>
</html>
