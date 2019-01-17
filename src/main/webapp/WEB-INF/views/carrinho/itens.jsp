<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Seu carrinho de compras">

	<jsp:attribute name="extraStyle">		
		<link rel="stylesheet" href='<c:url value="/resources/css/itens.css" />' >
	</jsp:attribute>
	
	<jsp:body>
		<div class="container">
		  <div class="heading">
		    <h1>Carrinho</h1>
		  </div>
		  
		  <div class="cart transition is-open">
		    <table class="table">
		      
		      <thead>
		     	 <tr>
			        <th>Produto</th>
			        <th>Preço</th>
			        <th>Quantidade</th>
			        <th>Total</th>
			        <th>Remover</th>
			     </tr>
		      </thead>
		      
		      <tbody>		     
			      <c:forEach items="${ carrinhoCompra.itens }" var="item">
				      <tr>
				        
				        <td>			         
				         ${item.produto.titulo }
				        </td>
				        
				        <td>
				          ${item.preco }
				        </td>
				
				        <td>        
				            <input name="quantidade" id="quantidade" type="numeric" value="${ carrinhoCompra.getQuantidade(item) }" />        
				        </td>
				        
				        <td>
				          R$ ${ carrinhoCompra.getTotal(item) }
				        </td>
				        
				        <td>
				          <form action="${ s:mvcUrl('CC#remover').arg(0, item.produto.id ).arg(1, item.tipo).build() }" method="post">
				          	<input type="submit" value="Remover" class="btn btn-remove" />
				          </form>
				        </td>
				      </tr>
			      </c:forEach>
		  	  </tbody>
		       <tfoot>
		        <tr>
		           <td colspan="3">Total:</td>
		           <td colspan="2"> R$ ${carrinhoCompra.getTotal()}</td>
		       	</tr>
		       </tfoot>         
		  </table> 
	    
	      <div class="row">
		  	<div class="col">
		 	 	<a href="<c:url value='/'/>">Voltar as compras</a>
			</div>
		  	<div class="col text-right">
			    <a href="#" class="btn btn-atualiza">Atualizar carrinho</a>
			  			
			  	<form:form action="${ s:mvcUrl('PC#Finalizar').build() }" method="post">
			  		<input type="submit" name="finalizar" value="Finalizar" class="btn btn-finaliza" />
			  	</form:form>
		  	 </div>
		   </div>
		  
		  
		 </div><!-- cart -->
	   </div><!-- container -->
	   
	</jsp:body>
	
</tags:pageTemplate>
