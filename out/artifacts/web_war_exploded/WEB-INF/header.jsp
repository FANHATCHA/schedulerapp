<header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-4 pt-1">
            <div class="dropdown">
                    <span>
                        <c:if test="${authUser != null}"><c:out value='${authUser.name}' /></c:if>
                    </span>
                <div class="dropdown-content">

                    <form action="/logout" method="post">
<%--                        <input type="hidden" name="id" value="${user.getId()}">--%>
    <a class="text-muted" href="javascript:;" onclick="parentNode.submit();">Log Out</a>
    <input type="hidden" name="mess" value="Logout"/>
                    </form>
                </div>
            </div>

        </div>
        <div class="col-4 text-center">
            <a class="text-dark" href="/dashboard">
                <h1>SchedulerApp</h1>
            </a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
            <button type="button" class="btn btn-success btn-lg"  id="myBtn">
                + Add new coursework
            </button>
        </div>
    </div>
</header>