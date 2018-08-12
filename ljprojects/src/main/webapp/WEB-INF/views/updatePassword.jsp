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
                      <div class="login-form">
    
        <div class="well lead">Change your password</div>
        
        <form:form method="POST" modelAttribute="updatePassword"  action="/admin/updatePassword" class="form-horizontal">
            
             <form:hidden path="token" value="${token}"/>
             <form:hidden path="userId" value="${userId}"/>
             
             <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="oldPassword"> Old Password</label>
                    <div class="col-md-7">
                        <form:input type="text" path="oldPassword" id="oldPassword" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="password" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
             
             
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="password">Password</label>
                    <div class="col-md-7">
                        <form:input type="text" path="password" id="password" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="password" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="confirmpasword">confirm password</label>
                    <div class="col-md-7">
                        <form:input type="text" path="confirmpasword" id="confirmpasword" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="confirmpasword" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-actions floatRight">
            
            <input type="submit" value="submit" class="btn btn-primary btn-sm"/>
            </div>
            </div>
            </form:form>

    </div>
    </div>
    </div>
    
    
</body>
</html>