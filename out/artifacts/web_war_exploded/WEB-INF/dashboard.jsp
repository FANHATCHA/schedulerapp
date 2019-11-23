<%--
Created by IntelliJ IDEA.
User: 23058
Date: 11/10/2019
Time: 1:27 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!Doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>SchedulerApp</title>
    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- google font  -->
    <link href='https://fonts.googleapis.com/css?family=Google+Sans:400,500&lang=en' rel='stylesheet' type='text/css'>

    <!-- Style-->
    <link rel="stylesheet" type="text/css" href="assets/css/dashboard.css">

    <!-- Datepicker Jquery -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#intended-due-date" ).datepicker({altFormat: "yyyy-mm-dd"});
            $( "#actual-completion-date" ).datepicker({altFormat: "yyyy-mm-dd"});
        } );
    </script>
</head>
<body>
<div class="container">
    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
                <a class="text-muted" href="#">Welcome !</a>
            </div>
            <div class="col-4 text-center">
                <a class="text-dark" href="/">
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
                    <th>Coursework Title</th>
                    <th>Module Title</th>
                    <th>Intended Completion Date</th>
                    <th>Actual Completion Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="coursework" items="${listCoursework}">

                    <tr>
                        <td><c:out value="${coursework.courseworkTitle}" /></td>
                        <td><c:out value="${coursework.moduleTitle}" /></td>
                        <td>
<%--                            <fmt:formatDate--%>
<%--                                    pattern="mm/dd/yyyy"--%>
<%--                                    value="${coursework.intendedDueDate}"--%>
<%--                                    type="Calendar"--%>
<%--                                    dateStyle="short" />--%>
                        </td>
                        <td>rti</td>
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
                <div class="row">
                    <input type="submit" name="submit" value="Save Coursework">
                </div>
            </form>

    </div>
</div>
</div>
<hr>
<footer class="blog-footer" style="margin-left:500px">
    <br>
    © copyright 2019 schedulerApp
</footer>
</body>
<script>
    // Get the modal
    var modal = document.getElementById("myModal");
    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");
    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];
    // When the user clicks the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }
    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</html>
