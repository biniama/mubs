<%@ page import="com.kaufda.mubs.model.BlogEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Mubs</title>
	</head>
	<body>
        <div class="container">
            <div class="jumbotron">
                <h2 style="text-align: center;">Mubs: For Your Blogging Needs!</h2>
            </div>
            <div class="row">
                <div class="col-md-12">

                    <g:if test="${flash.message}">
                        <div class="alert alert-info alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>${flash.message}</strong>
                        </div>
                    </g:if>

                    <g:hasErrors bean="${blogEntriesInstanceList}">
                        <ul class="errors" role="alert">
                            <g:eachError bean="${blogEntriesInstanceList}" var="error">
                                <li
                                    <g:if test="${error in org.springframework.validation.FieldError}">
                                        data-field-id="${error.field}"
                                    </g:if>>
                                    <div class="alert alert-danger">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <strong>${error}</strong>
                                    </div>
                                </li>
                            </g:eachError>
                        </ul>
                    </g:hasErrors>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h1>Title: ${blogEntryInstance?.title}</h1>
                            <h4>Author: ${blogEntryInstance?.blog?.user?.toString()}</h4>
                            <h5>Date: ${blogEntryInstance?.dateCreated}</h5>
                            <hr>
                            <h3>Blog: ${blogEntryInstance?.blog?.name}</h3>
                            <p>Description: ${blogEntryInstance?.blog?.description}</p>
                        </div>
                        <div class="panel-body">
                            <p>
                                ${blogEntryInstance?.content}
                            </p>

                            <sec:ifLoggedIn>
                                <g:form url="[resource:blogEntryInstance, action:'delete']" method="DELETE">
                                    <hr>
                                    <div>
                                        <g:link class="btn btn-warning" action="editBlogEntry" resource="${blogEntryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                    </div>
                                </g:form>
                            </sec:ifLoggedIn>
                        </div>
                    </div>
                </div>

            </div>

            <div class="pagination">
                <g:paginate total="${blogEntriesCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
