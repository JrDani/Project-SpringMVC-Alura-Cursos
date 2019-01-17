package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import daos.produtoDAO;
import models.CarrinhoCompra;
import models.CarrinhoItem;
import models.Produto;
import models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
	
	@Autowired
	private produtoDAO produtoDao;
	
	@Autowired
	private CarrinhoCompra carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipo) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem novoItem = addItem(produtoId, tipo);
		carrinho.add(novoItem);
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipo) {
		carrinho.remover(produtoId, tipo);
		return new ModelAndView("redirect:/carrinho");
	}
	
	
	private CarrinhoItem addItem(Integer id, TipoPreco tipo) {
		Produto produto = produtoDao.find(id);		
		return new CarrinhoItem(produto, tipo);
	}
	
	

}
