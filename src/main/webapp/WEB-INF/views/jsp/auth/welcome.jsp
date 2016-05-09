
	<%@ page pageEncoding="UTF-8" %>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>

	<jsp:include page="/WEB-INF/views/jsp/forms/header.jsp"/>
	You have been register in system<br>
	Welcome! <br><br>
	After 5 second You will be redirected to the home page.
	If it does not, or you do not want to wait, you can go on your own using next  <a href='/'>link</a>.
	<script language = 'javascript'>
	setTimeout("document.location.href='/'", 5000);
	</script>
	<jsp:include page="/WEB-INF/views/jsp/forms/footer.jsp"/>