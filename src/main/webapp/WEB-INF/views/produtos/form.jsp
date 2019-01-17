<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Formulário para um novo produto">

	<div class="container">
		<h1>Novo produto</h1>
		<form:form action="${s:mvcUrl('PC#insert').build() }" method="post" commandName="produto" enctype="multipart/form-data"
		cssClass="form pt-5 pb-5">
			<div class="form-group">
				<label>Título</label>
				<form:input path="titulo" cssClass="form-control" />
				<form:errors path="titulo" />
			</div>
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea path="descricao" rows="5" columns="15" cssClass="form-control" />
				<form:errors path="descricao" />
			</div>
			<div class="form-group">
				<label>Páginas</label>
				<form:input path="paginas" cssClass="form-control" />
				<form:errors path="paginas" />
			</div>
			
			<div class="form-group">
				<label>Data de lançamento</label>
				<form:input path="dataLancamento" cssClass="form-control" placeholder="dd/mm/YYYY" />
				<form:errors path="dataLancamento" />
			</div>
			
			<c:forEach items="${tipos}" var="item" varStatus="status"> 
				<div class="form-group">
					<label>${ item }</label>
					<form:input path="precos[${ status.index }].valor" class="form-control" />				
					<form:hidden path="precos[${ status.index }].tipo" value="${ item }" />	
					<form:errors path="precos" />			
				</div>
			</c:forEach>
			
			<div class="form-group">
				<label>Sumário</label>
				<input name="sumario" type="file" class="form-control" />
			</div>
			
			<input type="submit" value="Enviar" class="btn btn-primary"/>
		</form:form>
	</div>
	
</tags:pageTemplate>
