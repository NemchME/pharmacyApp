<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Список аптек</title>
</head>
<body>
<h1>Список аптек</h1>

<table border="1">
    <tr><th>ID</th><th>Название</th><th>Адрес</th><th>Телефон</th><th>Часы работы</th><th>Путь от центра</th></tr>
    <c:forEach var="p" items="${pharmacies}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.address}</td>
            <td>${p.phone}</td>
            <td>${p.workingHours}</td>
            <td>${p.wayFromCenter}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
