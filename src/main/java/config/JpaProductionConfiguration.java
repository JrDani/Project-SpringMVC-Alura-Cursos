package config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("prod")
public class JpaProductionConfiguration {
	
	@Autowired
	private Environment enviroment;
	
	@Bean	
	public Properties additionProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");		
		props.setProperty("hibernate.hbm2ddl.auto", "update"); //toda vez que mudarmos o modelo o hib.. muda no banco
		return props;
	}

	@Bean	
	public DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");

		//usuario:senha@host:port/path
		URI dbUrl = new URI(enviroment.getProperty("DATABASE_URL"));
		
		dataSource.setUrl("jdbc:postgresql://"+dbUrl.getHost()+":"+dbUrl.getPort()+dbUrl.getPath());
		dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
		dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);

		return dataSource;
	}
}
