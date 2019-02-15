package io.nagaita.mrs;

import io.nagaita.mrs.domain.service.user.ReservationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ReservationUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		//return new BCryptPasswordEncoder();
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				if (rawPassword == null || encodedPassword == null) {
					return false;
				}
				return rawPassword.toString().equals(encodedPassword);
			}
		};
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http.authorizeRequests()
				.antMatchers("/js/**", "/css/**").permitAll()
				.antMatchers("/**").authenticated()
				.and()
				.formLogin()
				.loginPage("/loginForm")
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/rooms/", true)
				.failureUrl("/loginForm?error=true").permitAll();
		//@formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
