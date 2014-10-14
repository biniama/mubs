<%@ page import="com.kaufda.mubs.model.User" %>

<%--
  Created by IntelliJ IDEA.
  User: Biniam
  Date: 10/11/2014
  Time: 7:41 AM
--%>
<!DOCTYPE html>
    <html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="user.profile.label" default="User Profile" /></title>
    </head>

    <body>
        <div class="container">
            <div class="row">

                <div class="col-sm-6">

                    <g:if test="${flash.message}">
                        <div class="alert alert-info alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong>${flash.message}</strong>
                        </div>
                    </g:if>

                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h3><g:message code="user.profile.label" default="User Profile" /></h3>
                        </div>

                        <g:form url="[resource:userInstance]" >

                            <div class="panel-body form-horizontal">

                                <g:if test="${userInstance?.firstName}">
                                    <div class="form-group">
                                        <label for="firstName" class="col-sm-4 control-label">
                                            <g:message code="user.firstName.label" default="First Name" />
                                        </label>
                                        <div class="col-sm-8">
                                            <span class="form-control" name="firstName" ><g:fieldValue bean="${userInstance}" field="firstName"/></span>
                                        </div>
                                    </div>
                                </g:if>

                                <g:if test="${userInstance?.lastName}">
                                    <div class="form-group">
                                        <label for="lastName" class="col-sm-4 control-label">
                                            <g:message code="user.lastName.label" default="Last Name" />
                                        </label>
                                        <div class="col-sm-8">
                                            <span class="form-control" id="lastName" ><g:fieldValue bean="${userInstance}" field="lastName"/></span>
                                        </div>
                                    </div>
                                </g:if>

                                <g:if test="${userInstance?.email}">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-4 control-label">
                                            <g:message code="user.email.label" default="Email" />
                                        </label>
                                        <div class="col-sm-8">
                                            <span class="form-control" id="email" ><g:fieldValue bean="${userInstance}" field="email"/></span>
                                        </div>
                                    </div>
                                </g:if>

                                <g:if test="${userInstance?.email}">
                                    <div class="form-group">
                                        <label for="gender" class="col-sm-4 control-label">
                                            <g:message code="user.gender.label" default="Gender" />
                                        </label>
                                        <div class="col-sm-8">
                                            <span class="form-control" id="gender" ><g:fieldValue bean="${userInstance}" field="gender"/></span>
                                        </div>
                                    </div>
                                </g:if>

                                <g:if test="${userInstance?.username}">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-4 control-label">
                                            <g:message code="user.username.label" default="Username" />
                                        </label>
                                        <div class="col-sm-8">
                                            <span class="form-control" id="username" ><g:fieldValue bean="${userInstance}" field="username"/></span>
                                        </div>
                                    </div>
                                </g:if>

                                <hr />

                                <g:if test="${userInstance?.blog?.name}">
                                    <div class="form-group">
                                        <label for="blogName" class="col-sm-4 control-label">
                                            <g:message code="blog.name.label" default="Blog Name" />
                                        </label>
                                        <div class="col-sm-8">
                                            <span class="form-control" id="blogName" >${userInstance?.blog?.name}</span>
                                        </div>
                                    </div>
                                </g:if>

                                <g:if test="${userInstance?.blog?.description}">
                                    <div class="form-group">
                                        <label for="blogDescription" class="col-sm-4 control-label">
                                            <g:message code="blog.description.label" default="Blog Description" />
                                        </label>
                                        <div class="col-sm-8">
                                            <g:textArea class="form-control" name="blogDescription" rows="10" value="${userInstance?.blog?.description}" disabled=""/>
                                        </div>
                                    </div>
                                </g:if>

                                <hr>

                                %{--Blog Entries--}%
                                <g:if test="${userInstance?.blog?.blogEntries}">
                                    <div class="col-sm-12">
                                        <h3>
                                            <g:message code="user.number.of.blog.entry.label" default="You have ${userInstance?.blog?.blogEntries.size()} blog entries."
                                                   args="${userInstance?.blog?.blogEntries.size()}"/>
                                        </h3>
                                    </div>

                                    <hr>
                                    ${totalNumberOfVisitsToAllBlogEntries}
                                    <div class="col-sm-12">
                                        <h4>
                                            <g:message code="user.number.of.visits.to.all.blog.entry.label" default="Your blog entries have been visited a total of ${totalNumberOfVisitsToAllBlogEntries} times."
                                                       args="${totalNumberOfVisitsToAllBlogEntries}"/>
                                        </h4>
                                    </div>
                                </g:if>

                                <hr>

                                <div class="form-group">
                                    <div class="col-sm-12 text-right">
                                        <g:link name="editUserProfile" class="btn btn-default" action="editUserProfile"
                                                resource="${userInstance}"><g:message code="user.edit.label" default="Edit User" />
                                        </g:link>
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
