<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Мясная Лавка</title>
</head>
<body>
<h1>Подробная информация</h1>
<#list images as image>
    <img src="/images/${image.id}" height="60px" alt=""><br><br>
</#list>
<b>Название: </b>${product.title}<br>
<b>Описание: </b>${product.description}<br>
<b>Цена: </b>${product.price}<br>
</body>
</html>