<#import "../shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <h1 style="text-align: center">Панель администратора</h1>
    <hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Активность</th>
            <th scope="col">Роли</th>
            <th scope="col">Бан</th>
            <th scope="col">Редактирование</th>
            <th scope="col">Подробная информация</th>
        </tr>
        <#list users as anotherUser>
            <tr>
                <th>${anotherUser.email}</th>
                <th>${anotherUser.phoneNumber}</th>
                <th><#if anotherUser.active>true<#else>false</#if></th>
                <th><#list anotherUser.roles as role>${role} </#list></th>
                <th>
                    <form action="/admin/user/ban/${anotherUser.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-light" value="Бан">
                    </form>
                </th>
                <th><a href="/admin/user/edit/${anotherUser.id}">Редактирование</a></th>
                <th><a href="/admin/user/${anotherUser.id}">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Пользователей нет</h3>
        </#list>
    </table>
    <hr>
    <h1 style="text-align: center">Создать новую позицию</h1><hr>
    <form action="/admin/products/create" method="post" enctype="multipart/form-data">
        Название позиции: <input type="text" name="title"><br><br>
        Описание: <input type="text" name="description"><br><br>
        Цена: <input type="number" step="0.01" name="price"><br><br>
        <select name="type">
            <option value="">Тип позиции</option>
            <option value="BURGER">Бургер</option>
            <option value="PIZZA">Пицца</option>
            <option value="SNACK">Закуска</option>
            <option value="DRINK">Напиток</option>
        </select><br><br>
        Первая картинка: <input type="file" name="file1"><br><br>
        Вторая картинка: <input type="file" name="file2"><br><br>
        Третья картинка: <input type="file" name="file3"><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить позицию">
    </form>
</@layout.template>