<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Форма заказа</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>${order != null ? "Редактирование заказа" : "Добавление заказа"}</h1>

<form action="orders" method="post">
    <input type="hidden" name="id" value="${order.id}" />

    id пользователя: <input type="number" name="userId" value="${order.userId}" required><br>
    id лекарства: <input type="number" name="medicineId" value="${order.medicineId}" required><br>
    id аптеки: <input type="number" name="pharmacyId" value="${order.pharmacyId}" required><br>
    Количество: <input type="number" name="quantity" value="${order.quantity}" required><br>
    Статус: <input type="number" name="quantity" value="${order.quantity}" required><br>
    <input type="submit" value="Сохранить">
</form>

<br>
<a href="orders">Назад к списку</a>
</body>
</html>
