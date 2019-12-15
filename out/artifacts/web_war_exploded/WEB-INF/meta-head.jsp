<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--Redirect to login page if not logged in--%>
<c:if test="${empty sessionScope.userSessionEmail}">
    <c:redirect url = "/"/>
</c:if>

<%--Displaying dashboard--%>
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

    <link rel="icon" type="image/jpg" href="assets/img/icon.png">

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