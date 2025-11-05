<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Форма производителя</title></head>
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

<div align="center">
<h1>${producer != null ? "Редактирование производителя" : "Добавление производителя"}</h1>
<form action="producers" method="post">
    <input type="hidden" name="id" value="${producer.id}" />

    <table border="0" cellpadding="6">
            <tr>
                <td align="right"><b>Название:</b></td>
                <td><input type="text" name="name" value="${producer.name}" size="40" required></td>
            </tr>
            <tr>
                <td align="right"><b>Страна:</b></td>
                <td><input type="text" name="country" value="${producer.country}" size="40" required></td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Сохранить">
                </td>
            </tr>
        </table>
</form>

<br>
<a href="producers">Вернуться к списку производителей</a>
</div>
</body>
</html>
