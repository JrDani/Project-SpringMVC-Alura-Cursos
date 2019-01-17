package controllers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import daos.produtoDAO;
import infra.FileSaver;
import models.Produto;
import models.TipoPreco;
import validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private produtoDAO produtoDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());	
	}
	
	@Autowired
	private FileSaver fileSaver;
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@CacheEvict(value="produtoHome", allEntries=true)
	public ModelAndView insert(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return form(produto);
		}
		
		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		
		produtoDao.gravar(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:/produtos");
	}
	
	//@RequestMapping(value="/produtos/", method=RequestMethod.GET) la emcima o /produtos foi configurado
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("produtos/listar");
		List<Produto> produtos = produtoDao.listar();
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable Integer id) {		
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");		
		Produto produto = produtoDao.find(id);
		modelAndView.addObject("produto", produto);		
		return modelAndView;
	}
	
	@ExceptionHandler(NoResultException.class)
	public String erro() {
		return "erros/produtoNaoEncontrado";
	}
	
}
