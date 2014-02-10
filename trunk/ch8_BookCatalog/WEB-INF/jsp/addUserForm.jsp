<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<portlet:actionURL var="addUserActionUrl">
	<portlet:param name="myaction" value="addUser" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="books" />
</portlet:renderURL>

<form:form name="addUserForm" commandName="user" method="post"
	action="${addUserActionUrl}">
	<table>
		<tr align="left">
			<a href="${homeUrl}">Home</a>
		</tr>
	</table>
	<table>
		<tr>
			<td>First Name:<font style="color: #C11B17;">*</font></td>
			<td><form:input path="fname" /></td>
		</tr>
		<tr>
			<td>Middle Name:<font style="color: #C11B17;">*</font></td>
			<td><form:input path="mname" /></td>
		</tr>
		<tr>
			<td>Last Name:<font style="color: #C11B17;">*</font></td>
			<td><form:input path="lname" /></td>
		</tr>
		<tr>
			<td>E-Mail:<font style="color: #C11B17;">*</font></td>
			<td><form:input path="email" /></td>
		</tr>		
	</table>
	<table align="right">
		<tr>
			<td><input type="submit" value="Add User" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>
<br></br>