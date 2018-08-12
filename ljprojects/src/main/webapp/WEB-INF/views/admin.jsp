
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
    
        <div class="well lead">
	Dear <strong>${user}</strong>, Welcome to Admin Page.
	<a href="<c:url value="/logout" />">Logout</a>
	
	</div>
    
    <a href="<c:url value="/admin/updatePassword" />">Change Password</a>   

    </div>
    
   <c:if test="${!empty listuser}">
 <table align="left" border="1">
  <tr>
   <th>User first Name</th>
   <th>User Last Name</th>
   <th>User Age</th>
  
  </tr>

  <c:forEach items="${listuser}" var="user">
   <tr>
    <td><c:out value="${user.firstName}"/></td>
    <td><c:out value="${user.lastName}"/></td>
    <td><c:out value="${user.email}"/></td>
   
   </tr>
  </c:forEach>
 </table>
</c:if>
    </div>
    </div>
    
    
    
    
</body>
</html>