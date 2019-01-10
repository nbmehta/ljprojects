<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html lang="en">
<head>
	<%@ include file = "include/include_css.jsp" %>
	</head>
<body>

<div class="wrapper">
    <%@ include file = "include/menu.jsp" %>
    <div class="main-panel">
		<%@ include file = "include/nav_fixed.jsp" %>
		
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Striped Table with Hover</h4>
                                <p class="category">Here is a subtitle for this table</p>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <th>Action</th>
                                        <th>ID</th>
                                    	<th>First Name</th>
                                    	<th>Last Name</th>
                                    	<th>User Name</th>
                                    	<th>Email</th>
                                    </thead>
                                    <tbody>
                  
                  			 <c:forEach items="${listuser}" var="user">
   								<tr>
   								 <td align="center">
                              <a class="btn btn-default" ><em class="fa fa-pencil"></em></a>
                           
                              <a  class="btn btn-default" href="<c:url value='/admin/delete/${user.id}' />" class="btn btn-danger custom-width"><em class="fa fa-trash"></em></a> 	
                            </td>
                                 <td><c:out value="${user.id}"/></td>
   								 <td><c:out value="${user.firstName}"/></td>
   								 <td><c:out value="${user.lastName}"/></td>
   								  <td><c:out value="${user.username}"/></td>
   								 <td><c:out value="${user.email}"/></td>
   
 								  </tr>
  							</c:forEach>
                  
                        
                        </tbody>
                                    </table>

                            </div>
                        </div>
                    </div>


                    <div class="col-md-12">
                        <div class="card card-plain">
                            <div class="header">
                                <h4 class="title">Table on Plain Background</h4>
                                <p class="category">Here is a subtitle for this table</p>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Name</th>
                                    	<th>Salary</th>
                                    	<th>Country</th>
                                    	<th>City</th>
                                    </thead>
                                    <tbody>
                                        <tr>
                                        	<td>1</td>
                                        	<td>Dakota Rice</td>
                                        	<td>$36,738</td>
                                        	<td>Niger</td>
                                        	<td>Oud-Turnhout</td>
                                        </tr>
                                        <tr>
                                        	<td>2</td>
                                        	<td>Minerva Hooper</td>
                                        	<td>$23,789</td>
                                        	<td>Curaçao</td>
                                        	<td>Sinaai-Waas</td>
                                        </tr>
                                        <tr>
                                        	<td>3</td>
                                        	<td>Sage Rodriguez</td>
                                        	<td>$56,142</td>
                                        	<td>Netherlands</td>
                                        	<td>Baileux</td>
                                        </tr>
                                        <tr>
                                        	<td>4</td>
                                        	<td>Philip Chaney</td>
                                        	<td>$38,735</td>
                                        	<td>Korea, South</td>
                                        	<td>Overland Park</td>
                                        </tr>
                                        <tr>
                                        	<td>5</td>
                                        	<td>Doris Greene</td>
                                        	<td>$63,542</td>
                                        	<td>Malawi</td>
                                        	<td>Feldkirchen in Kärnten</td>
                                        </tr>
                                        <tr>
                                        	<td>6</td>
                                        	<td>Mason Porter</td>
                                        	<td>$78,615</td>
                                        	<td>Chile</td>
                                        	<td>Gloucester</td>
                                        </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

        <%@ include file = "include/footer.jsp" %>
        

    </div>
</div>


</body>

  <%@ include file = "include/include_js.jsp" %>

</html>
