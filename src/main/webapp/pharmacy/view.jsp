<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head><title>Информация об аптеке</title></head>
<body>
<h1>Информация об аптеке</h1>

<p><b>ID:</b> ${pharmacy.id}</p>
<p><b>Название:</b> ${pharmacy.name}</p>
<p><b>Адрес:</b> ${pharmacy.address}</p>
<p><b>Телефон:</b> ${pharmacy.phone}</p>
<p><b>Часы работы:</b> ${pharmacy.workingHours}</p>
<p><b>Путь от центра:</b> ${pharmacy.wayFromCenter}</p>

<a href="${pageContext.request.contextPath}/pharmacyApp">Назад к списку</a>
</body>
</html>
