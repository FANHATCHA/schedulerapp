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
            <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-8 col-lg-6">
                    <h4><b style="color: #4aba70;"> <img src="assets/img/milestone-icon.png" alt="milestone-icon" width="40px;"> Add milestones</b></h4>
                    <h5>
                        <c:if test="${coursework != null}"><c:out value='${coursework.courseworkTitle}' /></c:if>
                    </h5>

                </div>
                <div class="col-sm-9 col-md-6 col-lg-6">
                    <div class="container">
                        <form class="form-inline" action="/milestone" method="post">

                            <div class="form-group mx-sm-5 mb-2">
                                <label for="milestone" class="">Milestone: </label>
                                <input type="text" name="milestone" class="form-control" id="milestone" placeholder="e.g Create Wireframe" required>
                            </div>
                            <input type="hidden" name="type-of-submission" value="newMilestone">
                            <c:if test="${authUser != null}">
                                <input type="hidden" name="auth-user-id" value="<c:out value='${authUser.id}' />" />
                            </c:if>
                            <c:if test="${coursework != null}">
                                <input type="hidden" name="coursework-id" value="<c:out value='${coursework.id}' />" />
                            </c:if>
                            <button type="submit" class="btn btn-success mb-2"> + Add</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
            <hr>

        <c:choose>
            <c:when test="${milestonesList.size() > 0}">
                <h6>
                    Number of milestones for <c:if test="${coursework != null}"><c:out value='${coursework.courseworkTitle}' /></c:if> (${milestonesList.size()})
                </h6>
                <br>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th><img src="assets/img/milestone-icon.png" alt="milestone-icon" width="20px;"> Milestones </th>
                        <th>Status</th>
                        <th>Mark as</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="milestone" items="${milestonesList}">
                        <tr>
                            <td><c:out value="${milestone.getMilestone()}" /></td>
                            <td>

                                <c:choose>
                                    <c:when test="${milestone.getIsCompleted() == 0}">
                                        <span style="color:red; text-transform: uppercase;"> X Not completed</span>
                                    </c:when>
                                    <c:when test="${milestone.getIsCompleted() == 1}">
                                        <span style="color:forestgreen; text-transform: uppercase;"> Completed</span>
                                    </c:when>
                                    <c:otherwise>
                                        <i>undefined</i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <th>
                                <form method="post" action="/milestone" >
                                    <select class="browser-default" id="status" name="status"  onchange='this.form.submit()'>
                                        <option value="" disabled selected>Choose an option</option>
                                        <option value="1">Completed</option>
                                        <option value="0">Not Completed</option>
                                    </select>
                                    <input type="hidden" name="type-of-submission" value="markAs">
                                    <c:if test="${authUser != null}">
                                        <input type="hidden" name="auth-user-id" value="<c:out value='${authUser.id}' />" />
                                    </c:if>
                                    <c:if test="${coursework != null}">
                                        <input type="hidden" name="coursework-id" value="<c:out value='${coursework.id}' />" />
                                    </c:if>
                                    <c:if test="${coursework != null}">
                                        <input type="hidden" name="milestone-id" value="<c:out value='${milestone.getId()}' />" />
                                    </c:if>
                                    <noscript><input type="submit" value="Submit"/></noscript>
                                </form>
                            </th>
                            <td>


                                <form method="post" action="/milestone" >

                                    <input type="hidden" name="type-of-submission" value="deleteMilestone">
                                    <c:if test="${authUser != null}">
                                        <input type="hidden" name="auth-user-id" value="<c:out value='${authUser.id}' />" />
                                    </c:if>
                                    <c:if test="${coursework != null}">
                                        <input type="hidden" name="coursework-id" value="<c:out value='${coursework.id}' />" />
                                    </c:if>
                                    <c:if test="${coursework != null}">
                                        <input type="hidden" name="milestone-id" value="<c:out value='${milestone.getId()}' />" />
                                    </c:if>
                                    <button class="btn btn-danger btn-sm" type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </c:when>

            <c:otherwise>
                <p style="color:red;">No Milestones found for this coursework project !</p>
            </c:otherwise>
        </c:choose>



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