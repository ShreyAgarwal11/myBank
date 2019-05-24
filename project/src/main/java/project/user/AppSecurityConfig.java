package project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	
@Autowired
	private UserDetailsService userDetailsService;
	/*@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return authProvider;
	}*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/user/**").authenticated()
			.and()
			.formLogin().loginPage("/login")//.loginProcessingUrl("/bank-login")
			.usernameParameter("email").passwordParameter("password")
			.defaultSuccessUrl("/user/{customerId}")
			.and()
			.logout()
			.logoutSuccessUrl("/welcome")
			.and()
			.exceptionHandling().accessDeniedPage("/denied")
			.and()
			.csrf().disable();
	}
}
