package controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import config.AppWebConfiguration;
import config.DataSourceConfigurationTest;
import config.JpaConfiguration;
import config.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={JpaConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class
		, SecurityConfiguration.class})
@ActiveProfiles("test")
public class HomeControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockmvc;
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() {
		this.mockmvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void deveRetornarParaHomeComLivros() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
			.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
				
	}
	@Test
	public void somenteAdminDeveAcessarProdutoForm() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.get("/produtos/form")
				.with(SecurityMockMvcRequestPostProcessors
						.user("teste@teste.com").password("teste").roles("USUARIO"))
				).andExpect(MockMvcResultMatchers.status().is(403));
	}

}
