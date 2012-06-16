<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>  
<c:url var="url" value="/" />
<script type="text/javascript">
function callAjax(){
	var id="1";

}
</script>
</head>
<body>
Test ${aoe}
<input type="button" onclick="callAjax()" value="aoe"/>
</body>
</html>