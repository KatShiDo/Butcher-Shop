<#import "../shared/layout.ftlh" as layout>
<@layout.template currentUser=user>
    <h1 style="text-align: center">Информация о пользователе</h1>
    <hr>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120" alt="">
        <br>
        <br>
        <h2>${targetUser.username}</h2>
        <br>
        Номер телефона: <b>${targetUser.phoneNumber}</b>
    </div>
    <br>
    <hr>
    <h1 style="text-align: center">Корзина пользователя ${targetUser.username}</h1>
    <br>
    <div class="row h-100 row-cols-3 row-cols-md-3 g-4">
        <#list products as product>
            <div class="col">
                <a href="/products/${product.id}" class="product-link">
                    <div class="card product">

                        <img src="/images/${product.previewImageId}"
                             class="card-img-top" alt="">
                        <div class="card-body">
                            <h5 class="card-title">${product.title}</h5>
                            <p class="card-text">${product.price} ₽</p>
                        </div>
                        <div class="card-footer">
                            <a href="/cart/delete/${product.id}">Удалить из корзины</a>
                        </div>
                    </div>
                </a>
            </div>

        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@layout.template>