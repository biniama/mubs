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
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <div class="col-sm-6">

                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h3><g:message code="user.signup.label" default="Sign Up" /></h3>
                        </div>

                        <g:form url="[resource:userInstance, action:'saveUser']" >
                            <div class="panel-body form-horizontal">
                                <g:render template="common" />

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
