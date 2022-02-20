package com.viceri.todo.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.viceri.todo.domain.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl implementacaoUserDetailsService;
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    	
    	.disable().authorizeRequests().antMatchers("/").permitAll()
    	
    	/*cors permitido nas tela inicial do sistema para login ou recuperar exemplos*/
    	.antMatchers("/index", "/**").permitAll()
    	
    	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
    	
    	.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
    	
    	//Mapeia url de logout e invalida o usuário
    	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    	
    	//Filtrar requisições de login para autenticação
        .and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
        		UsernamePasswordAuthenticationFilter.class)   	
    	
    	//filtrar demais requisições para verificar a presenção do token jwt no headerr http
    	.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);	
    }	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		//service que ira consultar o usuário no banco de dados
		auth.userDetailsService(implementacaoUserDetailsService)
		
		//padrão de codificação de senha criptografia
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
