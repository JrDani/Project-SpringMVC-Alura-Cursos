<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="extraStyle" fragment="true" %>
<%@ attribute name="extraScript" fragment="true" %>
<%@ attribute name="titulo" required="true" %>

<c:url value="/resources/css" var="pathCss" />
<c:url value="/resources/js" var="pathJs" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${ titulo }</title>
<link rel="stylesheet" href="${pathCss }/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<jsp:invoke fragment="extraStyle" />
</head>

<body>
<%@ include file="/WEB-INF/views/header.jsp" %>

<jsp:doBody></jsp:doBody>

</body>
<script src="${ pathJs }/bootstrap.min.js"></script>
<jsp:invoke fragment="extraScript" />
</html>