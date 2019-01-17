<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<c:url value="/resources/css" var="pathCss" />
<link rel="stylesheet" href="${pathCss }/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<h1>Login</h1>
		<form:form servletRelativeAction="/login" method="post">
			<div class="form-group">
				<label>Email</label>
				<input type="text" name="username" class="form-control"/>
			
			</div>
			<div class="form-group">
				<label>Senha</label>
				<input type="password" name="password" class="form-control" />
				
			</div>
			
			<input type="submit" value="Login" class="btn btn-primary"/>
		</form:form>
	</div>
</body>

</html>