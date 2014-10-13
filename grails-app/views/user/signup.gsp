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
        <title><g:message code="user.signup.label" default="Sign Up" /></title>
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

                    <g:hasErrors bean="${userInstance}">
                        <ul>
                            <g:eachError bean="${userInstance}" var="error">
                                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
                                    <div class="alert alert-info alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        <g:message code="default.unique.message" args="${error.arguments}"/>
                                    </div>
                                </li>
                            </g:eachError>
                        </ul>
                    </g:hasErrors>

                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h3><g:message code="user.signup.label" default="Sign Up" /></h3>
                        </div>

                        <g:form url="[resource:userInstance, action:'saveUser']" >
                            <div class="panel-body form-horizontal">
                                <g:render template="signupForm" />

                                <div class="form-group">
                                    <div class="col-sm-12 text-right">
                                        <g:submitButton name="signup" class="btn btn-default"
                                            value="${message(code: 'user.signup.label', default: 'Sign Up')}" />
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
