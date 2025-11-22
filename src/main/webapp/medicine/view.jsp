<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Информация о препарате</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h2>${medicine.id}</h2>
<p><strong>Торговое имя:</strong> ${medicine.trade_name}</p>
<p><strong>ИНН:</strong> ${medicine.inn}</p>
<p><strong>Дозировка:</strong> ${medicine.dosage}</p>
<p><strong>Форма:</strong> ${medicine.form}</p>
<p><strong>id поставщика:</strong> ${medicine.producerId}</p>

<a href="medicines">Назад</a>
</body>
</html>
