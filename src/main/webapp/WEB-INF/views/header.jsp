<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<header>
	<div class="container-fluid mb-5 bg-dark">
		<div class="container">
			<ul class="nav p-3">
			  <li class="nav-item">
			    <a class="nav-link text-white" href="${ s:mvcUrl('HC#home').build() }">Home</a>
			  </li>	
			  	
			  <security:authorize access="isAuthenticated()">
				  <li class="nav-item dropdown">
				    <a class="nav-link dropdown-toggle text-white" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><security:authentication property="principal.username" /></a>
				    <div class="dropdown-menu">
				      <a class="nav-link" href="${ s:mvcUrl('PC#listar').build() }"><fmt:message key="nav.produto.listar" /></a>
				      <a class="nav-link" href="${ s:mvcUrl('PC#form').build() }"><fmt:message key="nav.produto.novo" /></a>	
				      <div class="dropdown-divider"></div>
				      <a class="dropdown-item" href='<c:url value="/logout" />'><fmt:message key="nav.conta.sair" /></a>
				    </div>
				  </li>
			  </security:authorize>		  
			 
			  <security:authorize access="!isAuthenticated()">
				  <li class="nav-item">
				    <a class="nav-link text-white" href="${ s:mvcUrl('LC#loginForm').build() }">Login</a>
				  </li>	
			  </security:authorize>	
			  
			  <li class="nav-item dropdown">
				    <a class="nav-link dropdown-toggle text-white" data-toggle="dropdown" href="#" role="button" 
				    aria-haspopup="true" aria-expanded="false" rel="nofollow"><s:message code="nav.idioma" /></a>
				    <div class="dropdown-menu">
				      <a class="nav-link" href="?locale=pt"><s:message code="nav.pt" /></a>
				  	  <a class="nav-link" href="?locale=en_US" rel="nofollow"><s:message code="nav.en" /></a>
				    </div>
				  </li>
					  
			  <li class="nav-item ml-auto">
			    <a href="${ s:mvcUrl('CC#itens').build() }" class="nav-link text-white">			    	
			    	 <s:message code="nav.carrinho" arguments="${carrinhoCompra.quantidade}" />
			    </a>
			  </li>	 
			  
			</ul>
		 </div>
	</div>	
</header>