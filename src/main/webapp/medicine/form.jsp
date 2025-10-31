<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Форма препарата</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>${medicine != null ? "Редактирование препарата" : "Добавление препарата"}</h1>

<form action="medicines" method="post">
    <input type="hidden" name="id" value="${medicine.id}" />

    Торговое имя: <input type="text" name="tradeName" value="${medicine.tradeName}" required><br>
    ИНН: <input type="text" name="inn" value="${medicine.inn}" required><br>
    Дозировка: <input type="text" name="dosage" value="${medicine.dosage}" required><br>
    Форма: <input type="text" name="form" value="${medicine.form}" required><br>
    id поставщика: <input type="number" name="producerId" value="${medicine.producerId}" required><br>
    <input type="submit" value="Сохранить">
</form>

<br>
<a href="medicines">Назад к списку</a>
</body>
</html>
