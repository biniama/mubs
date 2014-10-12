<%--
  Created by IntelliJ IDEA.
  User: Biniam
  Date: 10/11/2014
  Time: 7:41 AM
--%>

<%@ page import="com.kaufda.mubs.model.BlogEntry" %>

<!DOCTYPE html>
    <html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="blog.entry.new.post.label" default="New Blog Post" /></title>
    </head>

    <body>
        <div class="container">
            <div class="row">

                <g:if test="${flash.message}">
                    <div class="alert alert-info alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <strong>${flash.message}</strong>
                    </div>
                </g:if>

                <g:hasErrors bean="${blogEntryInstance}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${blogEntryInstance}" var="error">
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

                <div class="col-sm-10">

                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h3><g:message code="blog.entry.new.post.label" default="New Blog Post" /></h3>
                        </div>

                        <g:form url="[resource:blogEntryInstance, action:'saveBlogEntry']" >
                            <div class="panel-body form-horizontal">

                                <g:render template="commonForm"/>

                                <div class="form-group">
                                    <div class="col-sm-12 text-right">
                                        <g:submitButton name="addBlogEntry" class="btn btn-default"
                                            value="${message(code: 'add.blog.entry.label', default: 'Add Blog Entry')}" />
                                    </div>
                                </div>

                            </div>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
