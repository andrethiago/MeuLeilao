package br.mp.mpf.meuleilao.infra.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select email,senha, ativo from usuario where email=?").authoritiesByUsernameQuery(
			"select u.email, p.papel from usuario u, usuario_papel p where u.id_usuario = p.id_usuario and u.email=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/resources/**").permitAll();
		http.authorizeRequests().antMatchers("/**/usuarios**/**").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/app/index.html");

		http.logout().logoutSuccessUrl("/login");
	}

}
