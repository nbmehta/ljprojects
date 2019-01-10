
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Portfolio</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Gallery</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      
      </ul>
    </div>
  </div>
</nav>


            <div class="container">
    
        <div class="well lead">Add city</div>
        <form:form method="POST" modelAttribute="cityModel" action="/addcity" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>
             
            <div class="row">
                <div class="form-group col-md-8">
                    <label class="col-md-3 control-lable" for="cityName">country Name</label>
                    <div class="col-md-5">
                        <form:input type="text" path="cityName" id="cityName" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="cityName" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
     
                  <div class="row">
                <div class="form-actions floatCenter">
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
   
   
<div class="container">
  <h2>City Table</h2>
            
  <table class="table">
    <thead>
      <tr>
                        <th><em class="fa fa-cog"></em></th>
                        <th class="hidden-xs">City Name</th>
                      
                        
     </tr>
    </thead>
    <tbody>
				<c:forEach items="${listcity}" var="city">
					<tr>
						<td align="center"><a class="btn btn-default"><em
								class="fa fa-pencil"></em></a> <a class="btn btn-default"
							href="<c:url value='/admin/deletecity/${city.id}' />"
							class="btn btn-danger custom-width"><em class="fa fa-trash"></em></a>
						</td>
						<td><c:out value="${city.cityName}" /></td>
						

					</tr>
				</c:forEach>
			</tbody>
  </table>
</div>
  




<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>

</body>
</html>





<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    
        <div class="well lead">Add city</div>
        <form:form method="POST" modelAttribute="cityModel" action="/addcity" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>
             
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="cityName">country Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="cityName" id="cityName" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="cityName" class="help-inline"/>
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
</html> --%>