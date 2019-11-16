<%--
  Created by IntelliJ IDEA.
  User: 23058
  Date: 11/10/2019
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SchedulerApp</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <!-- google font  -->
    <link href='https://fonts.googleapis.com/css?family=Google+Sans:400,500&lang=en' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="login-page">
    <div class="alert alert-success center" role="alert">
        <p>${NOTIFICATION}</p>
    </div>
    <div class="form">
        <h1 style="color:#000000">SchedulerApp </h1>
        <hr>
        <h3>Register</h3>

        <form action="/register-user" class="login-form" method="post">
            <input type="text"  name="name" placeholder="Name" required/>
            <input type="email"  name="email" placeholder="Email" required/>
            <input type="password" name="password" placeholder="Password" required/>
            <input type="password" name="confirm-password" placeholder="Confirm password" required/>
            <input type="submit" value="register" class="button" name="submit">
            <p class="message">Already registered? <a href="/">Log In</a></p>
        </form>
    </div>
</div>
</body>
</html>
