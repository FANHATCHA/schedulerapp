<%--
  Created by IntelliJ IDEA.
  User: Andrew Minja
  Date: 12/15/2019
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="meta-head.jsp" %>
<body>
<div class="container">
    <%@ include file="header.jsp" %>
</div>
<hr>
<main role="main" class="container">
    <div class="row">
        <div class="col-md-12">
            <h3>
                Edit <c:if test="${coursework != null}"><c:out value='${coursework.courseworkTitle}' /></c:if>
            </h3>
            <hr>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-3 col-md-6 col-lg-4">
                        <h5>
                            Summary of <c:if test="${coursework != null}"><c:out value='${coursework.courseworkTitle}' /></c:if>
                        </h5>
                        <ul>
                            <li>Module title: <c:if test="${coursework != null}"><c:out value='${coursework.moduleTitle}' /></c:if></li>
                            <li>Intended Due Date: <fmt:formatDate value="${coursework.getIntendedDueDate().getTime()}" pattern="mm/dd/yyyy"/></li>
                            <li>Actual Completion Date:<fmt:formatDate value="${coursework.getActualCompletionDate().getTime()}" pattern="mm/dd/yyyy"/></li>
                        </ul>
                    </div>
                    <div class="col-sm-9 col-md-6 col-lg-8">
                        <div class="container">
                            <form action="/save-coursework-project" method="post">
                                <c:if test="${coursework != null}">
                                    <input type="hidden" name="id" value="<c:out value='${coursework.id}' />" />
                                </c:if>

                                <div class="row">
                                    <div class="col-25">
                                        <label for="coursework-title">Title of coursework</label>
                                    </div>
                                    <div class="col-75">
                                        <input type="text" id="coursework-title" name="coursework-title"
                                               value="<c:out value='${coursework.courseworkTitle}' />"
                                               required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-25">
                                        <label for="module-title">Module title</label>
                                    </div>
                                    <div class="col-75">
                                        <input type="text" id="module-title" name="module-title"
                                               value="<c:out value='${coursework.moduleTitle}' />"
                                               required>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-25">
                                        <label for="intended-due-date">
                                            Intended due date
                                        </label>
                                    </div>
                                    <div class="col-25">
                                        <input type="text" id="intended-due-date"
                                               value="<fmt:formatDate value="${coursework.getIntendedDueDate().getTime()}" pattern="mm/dd/yyyy"/>"
                                               name="intended-due-date" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-25">
                                        <label for="actual-completion-date">Actual completion date</label>
                                    </div>
                                    <div class="col-25">
                                        <input type="text" id="actual-completion-date"
                                               value="<fmt:formatDate value="${coursework.getActualCompletionDate().getTime()}" pattern="mm/dd/yyyy"/>"
                                               name="actual-completion-date" required>
                                    </div>
                                </div>
                                <c:if test="${authUser != null}">
                                    <input type="hidden" name="auth-user-id" value="<c:out value='${authUser.id}' />" />
                                </c:if>
                                <c:if test="${authUser != null}">
                                    <input type="hidden" name="email" value="<c:out value='${authUser.email}' />" />
                                </c:if>
                                <div class="row">
                                    <input type="submit" name="submit" value="Update Coursework">
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>

        </div><!-- /main -->


    </div><!-- /.row -->

</main><!-- /.container -->

<!-- The Modal -->
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <hr>
        <h4>
            Enter coursework project details
        </h4>
        <p>

        <div class="container">
            <form action="/save-coursework-project" method="post">
                <div class="row">
                    <div class="col-25">
                        <label for="coursework-title">Title of coursework</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="coursework-title" name="coursework-title" placeholder="Title of coursework" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="module-title">Module title</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="module-title" name="module-title" placeholder="Module title" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="intended-due-date">
                            Intended due date
                        </label>
                    </div>
                    <div class="col-25">
                        <input type="text" id="intended-due-date" name="intended-due-date" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="actual-completion-date">Actual completion date</label>
                    </div>
                    <div class="col-25">
                        <input type="text" id="actual-completion-date" name="actual-completion-date" required>
                    </div>
                </div>
                <c:if test="${authUser != null}">
                    <input type="hidden" name="auth-user-id" value="<c:out value='${authUser.id}' />" />
                </c:if>
                <c:if test="${authUser != null}">
                    <input type="hidden" name="email" value="<c:out value='${authUser.email}' />" />
                </c:if>
                <div class="row">
                    <input type="submit" name="submit" value="Save Coursework">
                </div>
            </form>

        </div>
    </div>
</div>
<hr>
<%@ include file="footer.jsp" %>