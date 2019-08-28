<#import "parts/common.ftl" as c>

<@c.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control mr-3" placeholder="Search"
                       value="${filter?ifExists}">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>

    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new users
    </a>

    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method=" post" enctype="multipart/form-data">

                <div class="form-group">
                    <input type="text" class="form-control name=" name" placeholder="Введите имя пользователя">
                </div>

                <div class="form-group">
                    <input type="text" class="form-control name=" email" placeholder="Email">
                </div>

                <div class="custom-file">
                    <input type="file" name="file" id="customFIle">
                    <label class="custom-file-label" for="customFIle">Choose file</label>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group mt-3">
                    <label>
                        <input class="custom-file" type="number" name="age" placeholder="Возраст">
                    </label>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Внести</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns">
    <#list users as users>
        <div class="card my-3" >

            <#if users.filename??>
                <img class="card-img-top" src="/img/${users.filename}">
            </#if>
            <div class="m-2">
                <SPAN>${users.name}</SPAN>
                <SPAN>${users.email}</SPAN>
            </div>
            <div class="card-footer text-muted">
                ${users.accountName}
            </div>
        </div>
    <#else>
        No users
    </#list>
    </div>

    <span><a href="/user">User list</a> </span>
    </div>
    </div>
</@c.page>
