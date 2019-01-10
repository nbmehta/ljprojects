<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
   <head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		<link rel="stylesheet" href="${path}/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		<style type="text/css">
			body {
				background: url("http://quatangquangcao.net/wp-content/uploads/2016/02/AF-Client-Login-background-1024x682.png") no-repeat center center fixed;
				background-size: cover;
				display: flex;
				align-items: center;
				height: 100vh;
			}
		</style>
	</head>
 
    <body>
      <body>
    		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Forgot Password</strong>
						</div>
						<div class="panel-body">
							<form method="POST" modelAttribute="PasswordForgotDto"  action="/reset" class="form-signin form-horizontal">
													
								<div class="form-group">
									<div class="col-md-12">
										<div class="input-group input-group-md">
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-envelope"></span>
											</span>
											<input type="text" class="form-control" name="email" placeholder="Enter email"/>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									
								</div>
								
								<div class="form-group">
									<div class="col-md-12">
										<button type="submit" class="btn btn-default btn-block">
											<span class="glyphicon glyphicon-log-in"></span> Reset
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
       
</body>
</html>