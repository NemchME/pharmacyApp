<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Форма доступности препарата</title></head>
<body>

<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>${availabilityOfMedicine != null ? "Редактирование доступности препарата" : "Добавление доступности препарата"}</h1>

<form action="availabilityOfMedicines" method="post">
    <input type="hidden" name="id" value="${availabilityOfMedicine.id}" />

    id аптеки: <input type="number" name="pharmacyId" value="${availabilityOfMedicine.pharmacyId}" required><br>
    id препарата: <input type="number" name="medicineId" value="${availabilityOfMedicine.medicineId}" required><br>
    Цена: <input type="number" name="price" value="${availabilityOfMedicine.price}" required><br>
    Количество: <input type="number" name="quantity" value="${availabilityOfMedicine.quantity}" required><br>
    <input type="submit" value="Сохранить">
</form>

<br>
<a href="availabilityOfMedicines">Назад к списку</a>
</body>
</html>
