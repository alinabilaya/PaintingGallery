<%@ page pageEncoding="UTF-8" %>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>

	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

	<form:form id="userFormBean" commandName="userFormBean" action='${userFormAction}'  method="post">
	<table >
		<tr>
			<td class="Form_td1"  >
				<form:label path="user.name">Name:</form:label>
			</td>
			<td class="Form_td2"  >
				<form:input path="user.name" type="text" class="Form_input" maxlength="255"/>
			</td>
			<td class="Form_td3">
				${nameError}
			</td>
		</tr>
		<tr><td > </td></tr>
		<tr>
			<td class="Form_td1">
				<form:label path="user.username"> Login:</form:label>
			</td>
			<td class="Form_td2">
				<form:input path="user.username" type="text" class="Form_input" maxlength="255"/>
			</td>
			<td class="Form_td3">
				${usernameError}
			</td>
		</tr>
		<tr>
			<td class="Form_td1">
				<form:label path="user.password" >Password:</form:label>
			</td>
			<td class="Form_td2">
				<form:password path="user.password" class="Form_input form-control" maxlength="255"/>
			</td>
			<td class="Form_td3">
				${passwordError}
			</td>
		</tr>
		<tr>
			<td class="Form_td1">
				<form:label path="repeatedPassword">Confirm password:</form:label>
			</td>
			<td class="Form_td2">
				<form:password path="repeatedPassword" class="Form_input" maxlength="255"/>
			</td>
			<td class="Form_td3">
				${passwordConfirmError}
			</td>
		</tr>
		<tr><td > </td></tr>
		<tr>
			<td class="Form_td1">
				<form:label path="user.email">Email:</form:label>
			</td>
			<td class="Form_td2">
				<form:input path="user.email" type="text" class="Form_input" maxlength="255"/>
			</td>
			<td class="Form_td3">
				${emailError}
			</td>
		</tr>
		<tr><td > </td></tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" name="action" value="Sing Up"/>

				<input type="submit" name="cancel" value="Cancel"/>
			</td>
		</tr>
	</table>
	</form:form>