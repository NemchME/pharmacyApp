<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Информация о заказе</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h2>${order.id}</h2>
<p><strong>ID пользователя:</strong> ${order.userId}</p>
<p><strong>ID аптеки:</strong> ${order.medicineId}</p>
<p><strong>ID лекарства:</strong> ${order.pharmacyId}</p>
<p><strong>Количество:</strong> ${order.quantity}</p>
<p><strong>Статус:</strong> ${order.status}</p>
<p><strong>Время обновления:</strong> ${order.createdAt}</p>

<a href="orders">Назад</a>
</body>
</html>
