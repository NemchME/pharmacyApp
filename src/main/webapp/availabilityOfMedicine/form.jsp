<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Форма доступности препарата</title></head>
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
<h1>${availabilityOfMedicine != null ? "Редактирование доступности препарата" : "Добавление доступности препарата"}</h1>
<form action="availabilityOfMedicines" method="post">
    <input type="hidden" name="id" value="${availabilityOfMedicine.id}" />

            <table border="0" cellpadding="6">
                <tr>
                    <td align="right"><b>id аптеки:</b></td>
                    <td><input type="number" name="pharmacyId" value="${availabilityOfMedicine.pharmacyId}" size="40" required></td>
                </tr>
                <tr>
                    <td align="right"><b>id препарата:</b></td>
                    <td><input type="number" name="medicineId" value="${availabilityOfMedicine.medicineId}" size="40" required></td>
                </tr>
                <tr>
                    <td align="right"><b>Цена:</b></td>
                    <td><input type="number" name="price" value="${availabilityOfMedicine.price}" size="40" required></td>
                </tr>
                <tr>
                    <td align="right"><b>Количество:</b></td>
                    <td><input type="number" name="quantity" value="${availabilityOfMedicine.quantity}" size="40" required></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Сохранить">
                    </td>
                </tr>
                </table>
</form>

<br>
<a href="availabilityOfMedicines">Вернуться к списку доступности препаратов</a>
</div>
</body>
</html>
