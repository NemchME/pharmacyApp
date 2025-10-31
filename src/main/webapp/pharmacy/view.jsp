<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>Информация об аптеке</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h2>${pharmacy.name}</h2>
<p><strong>Адрес:</strong> ${pharmacy.address}</p>
<p><strong>Телефон:</strong> ${pharmacy.phone}</p>
<p><strong>Часы работы:</strong> ${pharmacy.workingHours}</p>
<p><strong>Как добраться:</strong> ${pharmacy.wayFromCenter}</p>

<a href="pharmacies">Назад</a>
</body>
</html>
