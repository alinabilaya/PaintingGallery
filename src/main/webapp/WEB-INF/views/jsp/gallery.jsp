<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href=“resources/css/some.css”>

<html>
   <head>
   <title>Welcome to my Gallery</title>
   </head>
   <body>

    <i> First awesome table!</i>
    <table>
        <thead>
        <tr>
            <th>Title</th>
            <th>Summary</th>
            <th>Artist</th>
            <th>Painting Gallery</th>
        </tr>
        <tbody>
            <c:forEach var="painting" items="${paintings}">
                <tr>
                    <td>${painting.title}</td>
                     <td>${painting.summary}</td>
                     <td>${painting.artist.name}</td>
                     <td>${painting.paintingGallery.owner}</td>
                   </tr>
            </c:forEach>
        </tbody>
    </table>
   </body>
</html>