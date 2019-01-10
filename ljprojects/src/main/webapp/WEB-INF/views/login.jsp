<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<strong>Login To Your Account</strong>
						</div>
						<div class="panel-body">
						 <c:url var="loginUrl" value="/login" />
							<form method="POST" action="${loginUrl}" class="form-signin form-horizontal">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Invalid username and password.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
								<div class="form-group">
									<div class="col-md-12">
										<div class="input-group input-group-md">
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-user"></span>
											</span>
											<input type="text" class="form-control" name="email" placeholder="Enter Username"/>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<div class="input-group input-group-md">
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-lock"></span>
											</span>
											<input type="password" class="form-control" name="password" placeholder="Enter Password" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="remember-me" id="remember-me"/> Remember Me
											</label>
											<label>
												<a href="<c:url value='/forgotPassword' />">forgot password?</a>
											</label>
										</div>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<div class="form-group">
									<div class="col-md-12">
										<button type="submit" class="btn btn-default btn-block">
											<span class="glyphicon glyphicon-log-in"></span> Login
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