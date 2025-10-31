<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Информация о пользователе</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h2>${user.username}</h2>
<p><strong>Пароль:</strong> ${user.passwordHash}</p>
<p><strong>Эл. почта:</strong> ${user.email}</p>
<p><strong>Роль:</strong> ${user.role}</p>

<a href="users">Назад</a>
</body>
</html>
