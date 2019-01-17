<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Oops!">
	<div class="container">
		<h2>Ocorreu algum erro!</h2>
		
		<!-- 
		Informação do erro para o desenvolvedor:
			Mensagem: ${ exception.message }
			<c:forEach items="${exception.stackTrace }" var="stk">
				${stk}
			</c:forEach>		
		 -->
		
	</div>
</tags:pageTemplate>