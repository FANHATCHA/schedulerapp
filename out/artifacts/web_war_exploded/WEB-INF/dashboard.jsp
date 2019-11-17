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
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- google font  -->
    <link href='https://fonts.googleapis.com/css?family=Google+Sans:400,500&lang=en' rel='stylesheet' type='text/css'>

    <!-- Style-->
    <link rel="stylesheet" type="text/css" href="assets/css/dashboard.css">

    <!-- Datepicker Jquery -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
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

<h2>
    SchedulerApp
    <div style='float: right;' id="new-message">
        <button class="button" id="myBtn">
            + Add new coursework
        </button>
        <h6>Hi, ${message}</h6>
    </div>
</h2>
<br>
<h3>List of courseworks posted(5)</h3>
<hr>
<div class="container">
    <img src="avatar.png" alt="Avatar" style="width:100%;">
    <p>Hello. How are you today?</p>
    <span class="time-right">11:00</span>
</div>

<div class="container darker">
    <img src="avatar.png" alt="Avatar" class="right" style="width:100%;">
    <p>Hey! I'm fine. Thanks for asking!</p>
    <span class="time-left">11:01</span>
</div>

<div class="container">
    <img src="avatar.png" alt="Avatar" style="width:100%;">
    <p>Sweet! So, what do you wanna do today?</p>
    <span class="time-right">11:02</span>
</div>

<div class="container darker">
    <img src="avatar.png" alt="Avatar" class="right" style="width:100%;">
    <p>Nah, I dunno. Play soccer.. or learn more coding perhaps?</p>
    <span class="time-left">11:05</span>
</div>
<!-- The Modal -->
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <p>
            Enter coursework project details
        </p>
        <p>

        <div class="container">
            <form action="/save-coursework-project" method="post">
                <div class="row">
                    <div class="col-25">
                        <label for="coursework-title">Title of coursework</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="coursework-title" name="coursework-title" placeholder="Title of coursework">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="module-title">Module title</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="module-title" name="module-title" placeholder="Module title">
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="intended-due-date">
                            Intended due date
                        </label>
                    </div>
                    <div class="col-25">
                        <input type="text" id="intended-due-date" name="intended-due-date">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="actual-completion-date">Actual completion date</label>
                    </div>
                    <div class="col-25">
                        <input type="text" id="actual-completion-date" name="actual-completion-date">
                    </div>
                </div>
                <div class="row">
                    <input type="submit" name="submit" value="Save Coursework">
                </div>
            </form>
        </p>
    </div>

</div>
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
</body>
</html>
