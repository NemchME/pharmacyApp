<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Информация о доступности препарата</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h2>${availabilityOfMedicine.id}</h2>
<p><strong>ID аптеки:</strong> ${availabilityOfMedicine.pharmacyId}</p>
<p><strong>ID препарата:</strong> ${availabilityOfMedicine.medicineId}</p>
<p><strong>Цена:</strong> ${availabilityOfMedicine.price}</p>
<p><strong>Количество:</strong> ${availabilityOfMedicine.quantity}</p>
<p><strong>Время обновления:</strong> ${availabilityOfMedicine.updatedAt}</p>

<a href="availabilityOfMedicines">Назад</a>
</body>
</html>
