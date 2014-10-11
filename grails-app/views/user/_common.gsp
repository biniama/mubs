<%--
  Created by IntelliJ IDEA.
  User: Biniam
  Date: 10/11/2014
  Time: 7:41 AM
--%>

<%@ page import="com.kaufda.mubs.model.User" %>

<div class="form-group">
    <label for="firstName" class="col-sm-4 control-label">
        <g:message code="user.firstName.label" default="First Name" />
    </label>
    <div class="col-sm-8">
        <g:textField class="form-control" name="firstName" value="${userInstance?.firstName}"/>
    </div>
</div>

<div class="form-group">
    <label for="lastName" class="col-sm-4 control-label">
        <g:message code="user.lastName.label" default="Last Name" />
    </label>
    <div class="col-sm-8">
        <g:textField class="form-control" name="lastName" value="${userInstance?.lastName}"/>
    </div>
</div>

<div class="form-group">
    <label for="email" class="col-sm-4 control-label">
        <g:message code="user.email.label" default="Email" />
    </label>
    <div class="col-sm-8">
        <g:textField class="form-control" name="email" value="${userInstance?.email}"/>
    </div>
</div>

<div class="form-group">
    <label for="gender" class="col-sm-4 control-label">
        <g:message code="user.gender.label" default="Gender" />
    </label>
    <div class="col-sm-8">
        <g:select class="form-control" name="gender" from="${com.kaufda.mubs.model.GenderTypeEnum?.values()}" keys="${com.kaufda.mubs.model.GenderTypeEnum.values()*.name()}" value="${userInstance?.gender?.name()}"  noSelection="['': '']"/>
    </div>
</div>

<div class="form-group">
    <label for="username" class="col-sm-4 control-label">
        <g:message code="user.username.label" default="Username" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-sm-8">
        <g:textField class="form-control" name="username" required="" value="${userInstance?.username}"/>
    </div>
</div>

<div class="form-group">
    <label for="password" class="col-sm-4 control-label">
        <g:message code="user.password.label" default="Password" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-sm-8">
        <g:textField class="form-control" name="password" required="" value="${userInstance?.password}"/>
    </div>
</div>

<div class="form-group">
    <label for="confirmPassword" class="col-sm-4 control-label">
        <g:message code="user.confirm.password.label" default="Confirm Password" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-sm-8">
        <g:textField class="form-control" name="confirmPassword" required="" value=""/>
    </div>
</div>
