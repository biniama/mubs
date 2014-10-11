
<%@ page import="com.kaufda.mubs.model.BlogEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'blogEntry.label', default: 'BlogEntry')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-blogEntry" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-blogEntry" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="blogEntry.createdByUser.label" default="Created By User" /></th>
					
						<th><g:message code="blogEntry.updatedByUser.label" default="Updated By User" /></th>
					
						<g:sortableColumn property="content" title="${message(code: 'blogEntry.content.label', default: 'Content')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'blogEntry.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="image" title="${message(code: 'blogEntry.image.label', default: 'Image')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'blogEntry.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${blogEntryInstanceList}" status="i" var="blogEntryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${blogEntryInstance.id}">${fieldValue(bean: blogEntryInstance, field: "createdByUser")}</g:link></td>
					
						<td>${fieldValue(bean: blogEntryInstance, field: "updatedByUser")}</td>
					
						<td>${fieldValue(bean: blogEntryInstance, field: "content")}</td>
					
						<td><g:formatDate date="${blogEntryInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: blogEntryInstance, field: "image")}</td>
					
						<td><g:formatDate date="${blogEntryInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${blogEntryInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
