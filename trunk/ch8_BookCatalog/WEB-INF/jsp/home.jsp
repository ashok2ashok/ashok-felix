<%@page import="chapter08.code.listing.domain.Book"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<portlet:defineObjects />

<liferay-theme:defineObjects />
<%
long groupId = scopeGroupId;
String name = portletDisplay.getRootPortletId();
String primKey = portletDisplay.getResourcePK();
String addBookButtonActionId = "ADD_BOOK_BUTTON";
String viewBookListActionId = "VIEW_BOOK_LIST";
%>



<portlet:renderURL var="showAddBookUrl">
	<portlet:param name="myaction" value="addBookForm" />
</portlet:renderURL>
<portlet:renderURL var="showUrl">
</portlet:renderURL>
<form:form name="catalogForm" method="post" action="${showAddBookUrl}">

<c:choose>
	<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, viewBookListActionId) %>">
		<c:if test="${not empty books}">
			<table border="1">
				<tr bgcolor="#99CCFF">
					<td valign="top"><b>Title</b></td>
					<td valign="top"><b>Author</b></td>
					<td valign="top"><b>ISBN Number</b></td>
					<td valign="top"><b>ACTION</b></td>
				</tr>
				<c:forEach var="book" items="${books}">
					<tr>
						<td valign="top"><c:out value="${book.name}" /></td>
						<td valign="top"><c:out value="${book.author}" /></td>
						<td valign="top"><c:out value="${book.isbnNumber}" /></td>
						<td align="center" valign="top" width="200px">
						
<%
name = Book.class.getName();
primKey = ((Book)pageContext.getAttribute("book")).getIsbnNumber()+"";
System.out.println("Primkey:"+primKey);
String editBookResourceActionId = "EDIT_BOOK";
String deleteBookResourceActionId = "DELETE_BOOK";
String permissionsResourceActionId = "PERMISSIONS";
%>						
						<c:choose>
							<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, editBookResourceActionId) %>">
								<a href="
									<portlet:renderURL>
										<portlet:param name="myaction" value="editBookForm" />
										<portlet:param name="isbnNumber" value="${book.isbnNumber}" />
									</portlet:renderURL>					
								"><b>Edit</b></a>
								/
							</c:when>
							<c:otherwise>
								#/
							</c:otherwise>
						</c:choose>						
						
						<c:choose>
							<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, deleteBookResourceActionId) %>">
								<a href="
									<portlet:actionURL>
										<portlet:param name="myaction" value="removeBook" />
										<portlet:param name="isbnNumber" value="${book.isbnNumber}" />
									</portlet:actionURL>					
								" onclick="javascript: return confirmRemove()"><b>Remove</b></a>
							</c:when>
							<c:otherwise>
								#
							</c:otherwise>
						</c:choose>
						
						
						<c:choose>
							<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, permissionsResourceActionId) %>">
								<liferay-security:permissionsURL
									modelResource="<%= name %>"
									modelResourceDescription="Book: ${book.name}"
									redirect="<%= showUrl %>"
									resourcePrimKey="${book.isbnNumber}"
									var="permissionsURL"
								/>
								
								<a href="<%= permissionsURL %>">Permissions</a>
							</c:when>
							<c:otherwise>
								#
							</c:otherwise>
						</c:choose>						
						

						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</c:when>
	<c:otherwise>
		You do not have permissions to view the book list!!!
	</c:otherwise>
</c:choose>

	
	<br></br>
<%
name = portletDisplay.getRootPortletId();
primKey = portletDisplay.getResourcePK();
%>
	<table align="right">
		<tr>
			<td>
			
			<c:choose>
				<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, addBookButtonActionId) %>">
					<input type="submit" value="Add Book" />
				</c:when>
				<c:otherwise>
					<input type="submit" value="Add Book" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
			
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</form:form>

	<portlet:renderURL var="showAddUserUrl">
		<portlet:param name="myaction" value="addUserForm" />
	</portlet:renderURL>
<form:form name="addUserButtonForm" method="post" action="${showAddUserUrl}">	
	<input type="submit" value="Add User" ></input>
</form:form>	


<br></br>