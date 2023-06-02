<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Мясная лавка</title>
</head>
<body>
<h1>Мясная лавка</h1>
<h2>Пользователь ${user.name}</h2>
<h3>Подробная информация</h3>
<b>Email: </b> ${user.email}
<b>Номер телефона: </b> ${user.phoneNumber}
<hr>
<h3>Корзина</h3>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} руб. | <a href="/products/${product.id}">Подробнее</a> </p>
    </div>
    <#else>
    <h4>Корзина пуста</h4>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
<a href="/login">Войти</a>
</body>
</html>