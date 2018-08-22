<!doctype html>
<html lang="en">
<head>
	<%@ include file = "include_css.jsp" %>
</head>
<body>

<div class="wrapper">
   <%@ include file = "menu.jsp" %>
   
    <div class="main-panel">
		<%@ include file = "nav_fixed.jsp" %>
		
        <div id="map"></div>

    </div>
</div>


</body>
<%@ include file = "include_js.jsp" %>
    <script>
        $().ready(function(){
            demo.initGoogleMaps();
        });
    </script>

</html>
