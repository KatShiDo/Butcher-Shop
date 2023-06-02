<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Мясная лавка</title>
</head>
<body>
<h1>Мясная лавка</h1>
<h4>Авторизация</h4>
<form action="/login" method="post">
    Эл. почта: <input type="email" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход">
</form>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>