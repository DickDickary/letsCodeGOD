<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="text" name="name" placeholder="Введите имя пользователя">
        <input type="text" name="email" placeholder="Email">
        <input type="file" name="file">
        <label>
            <input type="number" name="age" placeholder="Возраст">
        </label>
        <button type="submit">Внести</button>
    </form>

    <div>Список клиентов</div>

    <form method="get" action="/main">

        <label>
            <input type="text" name="filter"v value="${filter?ifExists}">
        </label>
        <button type="submit">Поиск</button>
    </form>


    <#list users as users>

        <div>
            <b>${users.id}</b>
            <SPAN>${users.name}</SPAN>
            <SPAN>${users.email}</SPAN>
            <strong>${users.accountName}</strong>
            <div>
                <#if users.filename??>
                    <img src="/img/${users.filename}">
                </#if>
            </div>
        </div>
    <#else>
        No users
    </#list>

    <@l.logout/>
    <span><a href="/user">User list</a> </span>

</@c.page>
