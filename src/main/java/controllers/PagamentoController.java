package controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import models.CarrinhoCompra;
import models.DadosPagamentos;
import models.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
	
	@Autowired
	private CarrinhoCompra carrinho;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailSender sender;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> Finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes ra) {
		
		return ()->{
			System.out.println(carrinho.getTotal());
			
			ModelAndView modelAndView = new ModelAndView("redirect:/produtos");		
			try {
				String url = "http://book-payment.herokuapp.com/payment";
				String response = restTemplate.postForObject(url, new DadosPagamentos(carrinho.getTotal()), String.class);
				ra.addFlashAttribute("sucesso", response);
				//enviaEmailCompraProduto(usuario);
				return modelAndView;
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				ra.addFlashAttribute("erro", "Erro ao realizar pagamento: valor maior que o permitido");
				return modelAndView;	
			}
		};
	}
	
	public void enviaEmailCompraProduto(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso - Daniel Cursos");
		email.setTo(usuario.getEmail());		
		email.setText("Compra em Daniel Cursos aprovada com sucesso. Valor: R$ " +carrinho.getTotal());
		email.setFrom("compras@danielcursos.com.br");
		
		sender.send(email);
	}
	
}
