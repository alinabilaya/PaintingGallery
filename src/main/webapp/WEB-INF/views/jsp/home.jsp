	<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>

<html>
<head>
    <title>Welcome to Gallery</title>
    <meta charset="utf-8">
    <link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Merriweather:400,700' rel='stylesheet' type='text/css'>
	  <link rel="stylesheet" href="/resources/css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" type="text/css" id="styles" href="/resources/css/styles1.css" media="screen">
  <!-- description -->
	  <link rel="stylesheet" href="/resources/css/shortcodes.css" type="text/css" media="screen">
	<!-- most popular paintings -->
	  <link rel="stylesheet" href="/resources/js/pretty/prettyPhoto.css" type="text/css" media="screen">
  <!-- slider -->
	  <link rel="stylesheet" href="/resources/js/nivo/nivo-slider.css" type="text/css" media="screen">

	<!--[if IE 7]><link rel="stylesheet" href="/resources/css/ie7.css" type="text/css" /><![endif]-->
</head>

<body>

<!-- HEADER -->
    <div class="dark-line"></div>

<!-- NAVIGATION MENU -->
        <div id="wrapper">

        <ul class="menu">
            <li><a class="curr" href="/">Home</a></li>

            <li><a href="/paintings">Paintings</a></li>

            <li><a href="/artists">Artists</a></li>

            <li><a href="/galleries">World museums</a></li>

             <sec:authorize access="!hasAnyRole('ADMIN', 'USER')">
             <li><a href="/login">LogIn</a></<li>
             </sec:authorize>

             <sec:authorize access="hasAnyRole('ADMIN', 'USER')">
             <li><a href="/logout">LogOut</a></<li>
             </sec:authorize>
        </ul>

<!-- CONTENT -->
        <div class="inner clearfix">

<!-- LOGO AND DESCRIPTION -->
            <div class="clearfix bottom-35">
                <a href="/" class="alignleft"><img src="/resources/images/content/logo.jpg" alt=""></a>
                <div class="description">
                    <sec:authorize access="hasAnyRole('ADMIN', 'USER')">
                        <h1> Hello, ${pageContext.request.remoteUser}!</h1>
                    </sec:authorize>
                    <h1>Welcome to my awesome gallery.</h1>
                    <p> You can search through our gallery for painting or for artist.
                     Also you can visit World museums page, where you can find useful information about most of all world museums.</p>
                </div>
            </div>

<!-- SLIDER -->
            <div class="main-slider bottom-50">
                <div id="slider" class="nivoSlider"><img src="/resources/images/content/slide1.jpg" alt="" title="#htmlcaption"> <img src="/resources/images/content/slide2.jpg" alt="" title="#htmlcaption1"> <img src="/resources/images/content/slide3.jpg" alt="" title="#htmlcaption2"></div>

                <div id="htmlcaption" class="nivo-html-caption">
                    <span>Art is everywhere...</span>
                </div>

                <div id="htmlcaption1" class="nivo-html-caption">
                    <span>And it is even closer than you can imagine...</span>
                </div>

                <div id="htmlcaption2" class="nivo-html-caption">
                    <span>Enjoy every moment of your life!</span>
                </div>

                <div id="preloader"></div>
            </div>


<!-- MOST POPULAR PAINTINGS PART -->
            <div class="divider-text bottom-50">
                <span>Most popular paintings</span>
            </div>

            <ul id="portfolio" class="inner-960 bottom-15 clearfix">
            <c:forEach var="painting" items="${paintings}">
                <li class="col1-4">
                    <div class="project">
                        <div class="proj-img"><a href="${painting.image}" class="prettyPhoto zoom"></a> <a href="/paintings/${painting.id}"></a> <img src="${painting.image}" alt=""></div>

                            <div class="proj-info">
                                <h4><a href="/paintings/${painting.id}">${painting.title}</a></h4>

                                <p>...</p>
                            </div>
                    </div>
                    </li>
            </c:forEach>
            </ul>

<!-- CONTENT ENDS-->
        </div>

<!-- FOOTER -->
        <div class="footer-wrap">
            <div class="copyrigts clearfix">
                <span class="owner">Copyright 2016  by <a href="">Alina B.</a> All rights reserved</span>
            </div>
        </div>
    </div>

    <div class="dark-line">
        <a href="#" class="totop"></a>
    </div>

   	<script type="text/javascript" src="/resources/js/jquery-1.7.1.min.js">
</script>
	<script type="text/javascript" src="/resources/js/jquery.effects.core.min.js">
</script>
	<script type="text/javascript" src="/resources/js/jPreloader/jquery.preloader.min.js">
</script>
	<script type="text/javascript" src="/resources/js/nivo/jquery.nivo.slider.pack.js">
</script>
    <script type="text/javascript" src="/resources/js/pretty/jquery.prettyPhoto.min.js">
</script>
	<script type="text/javascript" src="/resources/js/scrollTo/jquery.scrollTo-min.js">
</script>
	<script type="text/javascript" src="/resources/js/column-height.js">
</script>
	<script type="text/javascript" src="/resources/js/custom.js">
</script>
</body>
</html>