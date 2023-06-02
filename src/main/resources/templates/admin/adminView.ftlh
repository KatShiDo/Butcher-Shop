<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Мясная Лавка</title>
</head>
<body>
<h1>Мясная Лавка</h1>
<h2>Панель администратора</h2>
<hr>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Имя</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробнее</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phoneNumber}</th>
            <th>${user.name}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Бан">
                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Редактирование</a></th>
            <th><a href="/user/${user.id}">Подробнее</a></th>
        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>
</table>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} руб. | <a href="/products/${product.id}">Подробнее</a> </p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
<hr>
<h3>Создать новую позицию</h3>
<form action="/products/create" method="post" enctype="multipart/form-data">
    Название позиции: <input type="text" name="title"><br><br>
    Описание: <input type="text" name="description"><br><br>
    Цена: <input type="number" step="0.01" name="price"><br><br>
    Первая картинка: <input type="file" name="file1"><br><br>
    Вторая картинка: <input type="file" name="file2"><br><br>
    Третья картинка: <input type="file" name="file3"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить позицию">
</form>
</body>
</html>