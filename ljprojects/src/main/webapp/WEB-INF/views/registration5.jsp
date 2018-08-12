<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login page</title>
        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    </head>
 
    <body>
        <div id="mainWrapper">
            <div class="login-container">
                <div class="login-card">
                    <div class="login-form">
                        <c:url var="loginUrl" value="/registration" />
                        <form:form action="${loginUrl}" method="post" class="form-horizontal">
                            
                             <div class="input-group input-sm">
                                <label class="input-group-addon" for="name"><i class="fa fa-user"></i></label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="First Name" required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="lastName"><i class="fa fa-user"></i></label>
                                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="LastName Name" required>
                            </div>
                            
                             <div class="input-group input-sm">
                                <label class="input-group-addon" for="email"><i class="fa fa-user"></i></label>
                                <input type="text" class="form-control" id="email" name="email" placeholder="Email" required>
                            </div>
                            
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                 
                            <div class="form-actions">
                                <input type="submit"
                                    class="btn btn-block btn-primary btn-default" value="Log in">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
 
    </body>
</html>