package daos;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import builders.ProdutoBuilder;
import config.DataSourceConfigurationTest;
import config.JpaConfiguration;
import models.Produto;
import models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JpaConfiguration.class, produtoDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDaoTest {
	
	@Autowired
	private produtoDAO produtoDao;
	
	@Test
	@Transactional
	public void deveSomarTodosPrecosPorTipoLivro() {
		
		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN)
				.mais(3).buildAll();
		
		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN)
				.mais(3).buildAll();
		
		livrosImpressos.stream().forEach(produtoDao::gravar);
		livrosEbook.stream().forEach(produtoDao::gravar);
		
		BigDecimal valor = produtoDao.somaProdutoPorTipoPreco(TipoPreco.EBOOK);
		Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	}
	
}
