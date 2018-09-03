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
    
        <div class="well lead">Add state</div>
        <form:form method="POST" modelAttribute="stateModel" action="/addstate" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>
             
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="stateName">State Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="stateName" id="stateName" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="stateName" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
     
                  <div class="row">
                <div class="form-actions floatRight">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="save" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/login' />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="save" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/login' />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
    </div>
    </div>
    </div>
    
    
</body>
</html>