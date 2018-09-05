
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" scope="request">
	<%=basePath%>
</c:set>
<c:set var="apiPath" scope="request">
	http://localhost:1102/
</c:set>
<c:set var="adminPath" scope="request">
	http://localhost:1102/admin/
</c:set>
<link rel="stylesheet" type="text/css" href="${basePath}css/c1.css" />
<script type="text/JavaScript" src="${basePath}js/jquery-3.1.1.min.js"></script>
