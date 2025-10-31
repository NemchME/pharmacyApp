<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Форма аптеки</title>
</head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h2>${pharmacy.id == null ? "Добавить аптеку" : "Редактировать аптеку"}</h2>

<form action="${pageContext.request.contextPath}/pharmacies" method="post">
    <input type="hidden" name="id" value="${pharmacy.id}">

    Название:<input type="text" name="name" value="${pharmacy.name}" required><br>

    Адрес:<input type="text" name="address" value="${pharmacy.address}" required><br>

    Телефон:<input type="text" name="phone" value="${pharmacy.phone}" required><br>

    Часы работы:<input type="text" name="workingHours" value="${pharmacy.workingHours}" required><br>

    Путь от центра:<input type="text" name="wayFromCenter" value="${pharmacy.wayFromCenter}" required><br>

    <button type="submit">Сохранить</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/pharmacies?action=list">На главную</a>

</body>
</html>