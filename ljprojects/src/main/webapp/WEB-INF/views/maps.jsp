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
		
        <div id="map"></div>

    </div>
</div>


</body>
<%@ include file = "include/include_js.jsp" %>
    <script>
        $().ready(function(){
            demo.initGoogleMaps();
        });
    </script>

</html>
