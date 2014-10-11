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
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <div class="col-sm-10">

                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h3><g:message code="blog.entry.new.post.label" default="New Blog Post" /></h3>
                        </div>

                        <g:form url="[resource:blogEntryInstance, action:'saveBlogEntry']" >
                            <div class="panel-body form-horizontal">

                                <div class="form-group">
                                    <label for="blogTitle" class="col-sm-4 control-label">
                                        <g:message code="blog.title.label" default="Blog Title" />
                                    </label>
                                    <div class="col-sm-8">
                                        <g:textField class="form-control" name="blogTitle" value="${blogEntryInstance?.title}"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="blogContent" class="col-sm-4 control-label">
                                        <g:message code="blog.title.label" default="Blog Title" />
                                    </label>
                                    <div class="col-sm-8">
                                        <g:textArea class="form-control" name="blogContent" rows="15"
                                                    value="${blogEntryInstance?.content}"/>
                                    </div>
                                </div>

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
