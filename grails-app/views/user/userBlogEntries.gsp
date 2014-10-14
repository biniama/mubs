<%@ page import="com.kaufda.mubs.model.BlogEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Mubs</title>
	</head>
	<body>
        <div class="container">
           <div class="row">
                <div class="col-md-8">
                    
                    <h1><g:message code="blog.posts.label" default="Blog Posts" /></h1>

                    <g:if test="${blogEntriesCount > 0}">
                        <h4>${blogEntriesCount} blog entries found!</h4>
                    </g:if>
                    <g:else>
                        <h6>No blog entries found for the selected user.</h6>
                    </g:else>

                    <g:if test="${flash.message}">
                        <div class="alert alert-info alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>${flash.message}</strong>
                        </div>
                    </g:if>

                    <g:form url="[controller:'blogEntry', action:'blogEntryDetail']" >
                        <g:each in="${blogEntriesInstanceList}" status="i" var="blogEntryInstance">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h1>Title: ${blogEntryInstance?.title}</h1>
                                    <h3>Blog: ${blogEntryInstance?.blog?.name}</h3>
                                    <h4>Author: ${blogEntryInstance?.blog?.user?.toString()}</h4>
                                    <h5>Date: ${blogEntryInstance?.lastUpdated}</h5>
                                </div>
                                <div class="panel-body">
                                    <p>
                                        %{--Display to the point where the first full stop '.' sign is found.
                                         One is added to include the full stop in the content)--}%
                                        <g:set var="endPoint" value="${blogEntryInstance?.content?.indexOf('.') + 1}" />

                                        %{--Check if endPoint is null or less than or equal to zero and display the whole content.--}%
                                        <g:if test="${endPoint == null || endPoint <= 0}">
                                            <g:set var="endPoint" value="${blogEntryInstance?.content?.length() - 1}" />
                                        </g:if>

                                        %{--Display the content--}%
                                        ${blogEntryInstance?.content?.substring(0, endPoint)}
                                    </p>
                                    <hr>

                                    <div>
                                        <g:link controller="blogEntry" action="blogEntryDetail" id="${blogEntryInstance.id}" name="readMore"
                                                class="btn btn-info">${message(code: 'read.more.button.label', default: 'Read More')}</g:link>
                                    </div>
                                </div>
                            </div>
                        </g:each>
                    </g:form>
                </div>

                <div class="col-md-4">

                    <h2><g:message code="blog.list.entries.label" default="List of Blog Entries" /></h2>

                    <g:each in="${blogEntriesInstanceList}" status="i" var="blogEntryInstance">
                        <ul class="list-group">
                            <li class="list-group-item">
                            <span class="badge">${blogEntryInstance?.numberOfVisits}</span>

                                <g:link controller="blogEntry" action="blogEntryDetail" id="${blogEntryInstance.id}" name="readDetail">
                                    ${blogEntryInstance?.title}
                                </g:link>
                            </li>
                        </ul>
                    </g:each>
                </div>
            </div>

            <div class="pagination">
                <g:paginate total="${blogEntriesCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
