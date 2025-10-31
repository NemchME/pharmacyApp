<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Форма пользователя</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>${user != null ? "Редактирование пользователя" : "Добавление пользователя"}</h1>

<form action="users" method="post">
    <input type="hidden" name="id" value="${user.id}" />

    Никнейм: <input type="text" name="name" value="${user.name}" required><br>
    Пароль: <input type="password" name="country" value="${user.country}" required><br>
    Эл. почта: <input type="text" name="email" value="${user.email}" required><br>
    Роль: <input type="text" name="name" value="${user.role}" required><br>
    <input type="submit" value="Сохранить">
</form>

<br>
<a href="users">Назад к списку</a>
</body>
</html>
