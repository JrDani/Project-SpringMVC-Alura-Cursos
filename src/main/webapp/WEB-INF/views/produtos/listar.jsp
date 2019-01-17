<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Mostrando todos os produtos">
	<div class="container">
		
		<h1>Home</h1>
		
		<div>${ sucesso }</div>
		<div>${ erro }</div>
		
		<table class="table mt-5">
			<thead>
				<tr>	
					<th scope="col"><fmt:message key="lista.produto.titulo" /></th>
					<th scope="col"><fmt:message key="lista.produto.descricao" /></th>
					<th scope="col"><fmt:message key="lista.produto.paginas" /></th>
					<th scope="col"><fmt:message key="lista.produto.preco" /></th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${produtos }" var="produto">
			
				<tr>
					<td>
						<a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build() }">${produto.titulo }</a>
					</td>
					<td>${produto.descricao }</td>
					<td>${produto.paginas }</td>
					<td>${ produto.precos }</td>
				</tr>
			
			</c:forEach>
			</tbody>
		</table>
	</div>
</tags:pageTemplate>