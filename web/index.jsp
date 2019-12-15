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
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>SchedulerApp</title>

  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="icon" type="image/jpg" href="assets/img/icon.png">
  <link rel="stylesheet" href="assets/css/auth.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

  <!-- google font  -->
  <link href='https://fonts.googleapis.com/css?family=Google+Sans:400,500&lang=en' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="login-form">
  <form action="/login-auth" class="login-form" method="post">
    <div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
    <h4 class="modal-title">Login to SchedulerApp</h4>
    <div class="form-group">
      <input type="email"  name="email" placeholder="Email" required class="form-control"/>
          </div>
    <div class="form-group">
      <input type="password" class="form-control" name="password" placeholder="Password" required/>
    </div>
    <input type="submit" class="btn btn-primary btn-block btn-lg" value="login" name="submit">
  </form>
  <div class="text-center small">Don't have an account? <a href="/register">Sign up</a></div>
</div>
</body>
</html>
