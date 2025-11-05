<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Форма заказа</title></head>
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


<div align="center">
<h1>${order != null ? "Редактирование заказа" : "Добавление заказа"}</h1>
<form action="orders" method="post">
    <input type="hidden" name="id" value="${order.id}" />

    <table border="0" cellpadding="6">
        <tr>
            <td align="right"><b>id пользователя:</b></td>
            <td><input type="number" name="userId" value="${order.userId}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>id лекарства:</b></td>
            <td><input type="number" name="medicineId" value="${order.medicineId}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>id аптеки:</b></td>
            <td><input type="number" name="pharmacyId" value="${order.pharmacyId}" size="40" required></td>
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
