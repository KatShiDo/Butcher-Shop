<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Мясная Лавка</title>
</head>
<body>
<h1>Мясная Лавка</h1>
<#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти">
    </form>
    <#if user.isAdmin()>
        <a href="/admin">Панель администратора</a>
    </#if>
<#else>
    <a href="/login">Войти</a>
</#if>
<form action="/" method="get">
    Поиск по названию: <input type="text" name="title"><br>
    <input type="submit" value="Поиск">
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} руб. | <a href="/products/${product.id}">Подробнее</a> </p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>
</body>
</html>