<#import "../shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <h1 style="text-align: center">Авторизация</h1>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">Имя пользователя</label>
            <input type="text" class="form-control <#if loginError??>is-invalid</#if>" id="username" name="username">
            <div id="email" class="invalid-feedback">
                Неверное имя пользователя
            </div>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control <#if loginError??>is-invalid</#if>" id="password" name="password">
            <div id="password" class="invalid-feedback">
                Неверный пароль
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <br>
        <button type="submit" style="width: 100%" class="btn btn-dark">Войти</button>
    </form>
    <hr>
    <h2>Нет аккаунта в Мясной Лавке? <a href="/registration">Зарегистрируйся!</a></h2>
</@layout.template>