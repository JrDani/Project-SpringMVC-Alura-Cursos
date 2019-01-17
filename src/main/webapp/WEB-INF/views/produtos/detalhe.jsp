<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="${ produto.titulo }">

<div class="container">

	<div class="row mt-5">

		<div class="col-sm">
			<h1>${produto.titulo }</h1>
			<p>Edição: <fmt:formatDate pattern="dd/MM/yyyy" value = "${produto.dataLancamento.time }"/></p>
			<p>${produto.descricao }</p>
			
		</div>

		<div class="col-sm">
			<h2>Informações:</h2>
			<p>Número de páginas: ${produto.paginas }</p>
			 
			<h2 class="mt-5">Efetue sua compra:</h2>
			<form:form servletRelativeAction="/carrinho/add" method="post">
				<input type="hidden" name="produtoId" value="${produto.id }" />
				
				<div class="row mt-4 mb-2">
					<c:forEach items="${produto.precos }" var="preco">
					
					<div class="col">
						<input type="radio" name="tipo" value="${preco.tipo }" checked>				
						<label><strong>${preco.tipo }: </strong></label>				
						<p><small style="margin-left:10px">R$ ${preco.valor }</small></p>		
					</div>
					
					</c:forEach>
				</div>
				
				<div>
					<input type="submit" value="Comprar" class="btn btn-primary"/>
				</div>
			</form:form>
		</div>

	</div>
</div>

</tags:pageTemplate>