<%--
Created by IntelliJ IDEA.
User: 23058
Date: 11/10/2019
Time: 1:27 PM
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
            <h3 class="pb-4 mb-4 font-italic border-bottom">
                List of courseworks (<c:out value="${sizeOfCoursework}" />)
            </h3>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Add milestones<img src="assets/img/milestone-icon.png" alt="milestone-icon" width="20px;"></th>
                    <th>Module Title</th>
                    <th>Intended Completion Date</th>
                    <th>Actual Completion Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="coursework" items="${listCoursework}">

                    <tr>
                        <td>
                           <b>+</b> <a href="add-milestones-to-coursework?id=<c:out value='${coursework.id}' />&i=<c:out value='${authUser.id}' />"><c:out value="${coursework.courseworkTitle}" /></a>
                        </td>
                        <td><c:out value="${coursework.moduleTitle}" /></td>
                        <td>
                            <fmt:formatDate value="${coursework.getIntendedDueDate().getTime()}" pattern="mm/dd/yyyy"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${coursework.getActualCompletionDate().getTime()}" pattern="mm/dd/yyyy"/>
                        </td>

                        <td>
                            <a class="btn btn-success btn-sm" href="edit-coursework-project?id=<c:out value='${coursework.id}' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="btn btn-danger btn-sm" href="delete-coursework-project?id=<c:out value='${coursework.id}' />">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
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