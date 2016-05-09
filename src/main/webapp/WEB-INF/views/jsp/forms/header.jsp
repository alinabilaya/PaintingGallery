
	<!DOCTYPE html>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ page pageEncoding="UTF-8" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>

	<head>
	<link rel="stylesheet" type="text/css" href="/resources/css/styles1.css" media="screen">
	<link rel="stylesheet" type="text/css" href="/resources/css/singUp_form.css" media="screen">
	<link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700' rel='stylesheet' type='text/css'>
	<title>Sing up</title>
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

             <sec:authorize access="!hasAnyRole('ADMIN', 'USER')">
             <li><a href="/login">LogIn</a></<li>
             </sec:authorize>
        </ul>

  <div class="box-form">