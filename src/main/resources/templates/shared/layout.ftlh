<#macro template currentUser>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
              crossorigin="anonymous">

        <title>Мясная лавка</title>
    </head>
    <body>
    <nav class="navbar bg-dark border-bottom border-bottom-dark navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand mb-0 h1" href="/">Мясная Лавка</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Меню
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/products/burgers">Бургеры</a></li>
                            <li><a class="dropdown-item" href="/products/pizza">Пицца</a></li>
                            <li><a class="dropdown-item" href="/products/snacks">Закуски</a></li>
                            <li><a class="dropdown-item" href="/products/drinks">Напитки</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <#if currentUser.email??>
                <div class="dropdown text-end">
                    <a href="/profile">
                        <img src="/static/images/avatar.png" alt="mdo"
                             width="32" height="32" class="rounded-circle">
                    </a>
                </div>
            <#else>
                <button class="btn btn-light my-2 my-sm-0" type="button"
                        onclick="window.location.href = '/login'">
                    Войти
                </button>
            </#if>


            <#--<a href="/">
                <span class="navbar-brand mb-0 h1">Мясная Лавка</span>
            </a>
            <#if currentUser.email??>
                <div class="dropdown text-end">
                    <a href="/profile">
                        <img src="/static/images/avatar.png" alt="mdo"
                             width="32" height="32" class="rounded-circle">
                    </a>
                </div>
            <#else>
                <button class="btn btn-light my-2 my-sm-0" type="button"
                        onclick="window.location.href = '/login'">
                    Войти
                </button>
            </#if>-->
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>