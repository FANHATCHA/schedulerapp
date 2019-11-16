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
  <title>SchedulerApp</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css"/>
  <!-- google font  -->
  <link href='https://fonts.googleapis.com/css?family=Google+Sans:400,500&lang=en' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="login-page">
  <div class="form">
    <h1 style="color:#000000">SchedulerApp</h1>
<%--    <div class="alert">--%>
<%--      <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>--%>
<%--       ${message}--%>
<%--    </div>--%>
    <form class="register-form">
      <input type="text" placeholder="name"/>
      <input type="password" placeholder="password"/>
      <input type="text" placeholder="email address"/>
      <button>create</button>
      <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>
    <form action="/login-auth" class="login-form" method="post">
      <input type="email"  name="email" placeholder="Email" required/>
      <input type="password" name="password" placeholder="Password" required/>
      <input type="submit" value="login" class="button" name="submit">
      <p class="message">Not registered? <a href="/register">Create an account</a></p>
    </form>
  </div>
</div>
</body>
</html>
