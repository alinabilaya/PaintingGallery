	<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
<link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="/resources/css/styles1.css" media="screen">
<link rel="stylesheet" type="text/css" href="/resources/css/login_page.css" media="screen">
<title>Log in page</title>
</head>
<body>

<!-- HEADER -->
    <div class="dark-line"></div>

<!-- NAVIGATION MENU -->
        <div id="wrapper">

        <ul class="menu">
            <li><a href="/">Home</a></li>

            <li><a href="/paintings">Paintings</a></li>

            <li><a href="/artists">Artists</a></li>

            <li><a href="/galleries">World museums</a></li>
        </ul>

  <div class="login-form">
    <c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="post" class="box login">

        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p>Invalid username or password.</p>
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p>You have been logged out successfully.</p>
            </div>
        </c:if>

		<fieldset class="boxBody">
			<div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
		</fieldset>

		<footer>
		<a href="/auth/singUp">Sing up</a>
			<input type="submit" class="btnLogin" value="Sign In" tabindex="4">
		</footer>
	</form>
  </div>

</body>
