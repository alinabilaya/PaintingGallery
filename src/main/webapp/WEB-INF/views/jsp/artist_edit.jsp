	<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>

<html>
<head>
    <meta charset="utf-8">
    <link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Merriweather:400,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="/resources/css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" type="text/css" id="styles" href="/resources/css/styles1.css" media="screen">
    <link rel="stylesheet" type="text/css" id="styles" href="/resources/css/painting_edit.css" media="screen">

<title>Edit ${artist.name}</title>
</head>
<body>
<!-- HEADER -->
    <div class="dark-line"></div>

<!-- NAVIGATION MENU -->
    <div id="wrapper">

        <ul class="menu">
            <li><a href="/">Home</a></li>

            <li><a href="/paintings">Paintings</a></li>

            <li><a class="curr" href="/artists">Artists</a></li>

            <li><a href="/galleries">World museums</a></li>

            <sec:authorize access="hasAnyRole('ADMIN', 'USER')">
            <li><a href="/logout">LogOut</a></<li>
            </sec:authorize>
        </ul>

        <div class=backButton><a href="/artists/${artist.id}">Back to artist</a></div>
        <div class="edit-box">
            <form:form modelAttribute="artist" action="/edit/artists/${artist.id}">
                <div class="label"> Name:</div>
                <div class="width-setter"><form:input path="name" type="text"/></div>

                <div class="label">Country:</div>
                <div class="width-setter"><form:input path="country" type="text"/></div>

                <div class="label">Info:</div>
                <div class="width-setter"><form:textarea path="info" type="text" maxlength="20000" rows="10"/></div>

                <div class="label">Image path:</div>
                <div class="width-setter"><form:input path="image" type="text"/></div>

                <div class="button"><input type="submit" value="Save Changes"/></div>
            </form:form>
        </div>
    </div>
</body>
</html>