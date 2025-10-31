<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Форма производителя</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>${producer != null ? "Редактирование производителя" : "Добавление производителя"}</h1>

<form action="producers" method="post">
    <input type="hidden" name="id" value="${producer.id}" />

    Название: <input type="text" name="name" value="${producer.name}" required><br>
    Страна: <input type="text" name="country" value="${producer.country}" required><br>

    <input type="submit" value="Сохранить">
</form>

<br>
<a href="producers">Назад к списку</a>
</body>
</html>
