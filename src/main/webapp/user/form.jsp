<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Форма пользователя</title></head>
<body bgcolor="#e6f2ff">
<div align="center" style="margin-bottom: 20px;">
    <table border="0" cellpadding="8" bgcolor="#cce5ff">
        <tr>
            <td><a href="stats"><b>Статистика</b></a></td>
            <td><a href="availabilityOfMedicines?page=1&size=5"><b>AvailabilityOfMedicines</b></a></td>
            <td><a href="medicines?page=1&size=5"><b>Medicine</b></a></td>
            <td><a href="orders?page=1&size=5"><b>Order</b></a></td>
            <td><a href="pharmacies?page=1&size=5"><b>Pharmacy</b></a></td>
            <td><a href="producers?page=1&size=5"><b>Producer</b></a></td>
            <td><a href="users?page=1&size=5"><b>User</b></a></td>
        </tr>
    </table>
</div>
</div>


<div align="center">
<h1>${user.id != null ? "Редактирование пользователя" : "Добавление пользователя"}</h1>
<form action="users" method="post">
    <input type="hidden" name="id" value="${user.id}" />

<table border="0" cellpadding="6">
        <tr>
            <td align="right"><b>Никнейм:</b></td>
            <td>
                <input type="text" name="username" value="${user.username}" size="40" required><br>
                            <c:if test="${not empty usernameError}">
                                <span style="color: red; font-size: 12px;">
                                    ${usernameError}
                                </span>
                            </c:if>

                </td>
        </tr>
        <tr>
            <td align="right"><b>Пароль:</b></td>
            <td><input type="password" name="passwordHash" value="${user.passwordHash}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>Эл. почта:</b></td>
            <td><input type="text" name="email" value="${user.email}" size="40" required></td>
        </tr>
        <tr>
            <td align="right"><b>Роль:</b></td>
            <td><input type="text" name="role" value="${user.role}" size="40" required></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Сохранить">
            </td>
        </tr>
    </table>
</form>

<br>
<a href="users">Вернуться к списку пользователей</a>
</div>
</body>
</html>
