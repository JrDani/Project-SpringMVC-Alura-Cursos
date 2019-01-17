package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//chave = carrinho item e valor = quantidade
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem novoItem) {
		itens.put(novoItem, getQuantidade(novoItem)+1);
	}

	public Integer getQuantidade(CarrinhoItem novoItem) {
		if(!itens.containsKey(novoItem)) { //padrão usando hashcode do Object, implementar o do Produto
			itens.put(novoItem, 0);
		}
		return itens.get(novoItem);
	}
	
	public Integer getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo+acumulador);
	}

	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
		
	}

	public void remover(Integer produtoId, TipoPreco tipo) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		
		this.itens.remove(new CarrinhoItem(produto, tipo));
	}
	
	

}
