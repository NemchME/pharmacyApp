<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Форма аптеки</title>
</head>
<body bgcolor="#e6f2ff">
<body>
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


<div align="center">
<h2>${pharmacy.id == null ? "Добавить аптеку" : "Редактировать аптеку"}</h2>
<form action="pharmacies" method="post">
    <input type="hidden" name="id" value="${pharmacy.id}">

    <table border="0" cellpadding="6">
        <tr>
            <td align="right"><b>Название:</b></td>
            <td><input type="text" name="name" value="${pharmacy.name}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>Адрес:</b></td>
            <td><input type="text" name="address" value="${pharmacy.address}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>Телефон:</b></td>
            <td><input type="text" name="phone" value="${pharmacy.phone}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>Часы работы:</b></td>
            <td><input type="text" name="workingHours" value="${pharmacy.workingHours}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>Путь от центра:</b></td>
            <td><input type="text" name="wayFromCenter" value="${pharmacy.wayFromCenter}" size="40" required></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Сохранить">
            </td>
        </tr>
        </table>
</form>

<br>
<a href="pharmacies">Вернуться к списку аптек</a>
</div>

</body>
</html>