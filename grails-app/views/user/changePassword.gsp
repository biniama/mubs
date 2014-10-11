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
        <title><g:message code="user.change.password.label" default="Change Password" /></title>
    </head>

    <body>
        <div class="container">
            <div class="row">
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <div class="col-sm-6">

                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h3><g:message code="user.change.password.label" default="Change Password" /></h3>
                        </div>

                        <g:form url="[resource:userInstance, action:'changePasswordConfirm']" >
                            <div class="panel-body form-horizontal">

                                <div class="form-group">
                                    <label for="oldPassword" class="col-sm-4 control-label">
                                        <g:message code="user.old.password.label" default="Old Password" />
                                        <span class="required-indicator">*</span>
                                    </label>
                                    <div class="col-sm-8">
                                        <g:passwordField class="form-control" name="oldPassword" required="" value=""/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="newPassword" class="col-sm-4 control-label">
                                        <g:message code="user.new.password.label" default="New Password" />
                                        <span class="required-indicator">*</span>
                                    </label>
                                    <div class="col-sm-8">
                                        <g:passwordField class="form-control" name="newPassword" required="" value=""/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="confirmNewPassword" class="col-sm-4 control-label">
                                        <g:message code="user.confirm.new.password.label" default="Confirm New Password" />
                                        <span class="required-indicator">*</span>
                                    </label>
                                    <div class="col-sm-8">
                                        <g:passwordField class="form-control" name="confirmNewPassword" required="" value=""/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 text-right">
                                        <g:submitButton name="signup" class="btn btn-default"
                                            value="${message(code: 'user.change.password.label', default: 'Change Password')}" />
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
