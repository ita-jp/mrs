package io.nagaita.mrs;

import io.nagaita.mrs.domain.model.RoleName;
import io.nagaita.mrs.domain.service.user.ReservationUserDetailsService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ReservationUserDetailsService userDetailsService;
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

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
				.mvcMatchers("/js/**", "/css/**").permitAll()
				.mvcMatchers("/admin/**").hasRole(RoleName.ADMIN.name())
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/loginForm")
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(authenticationSuccessHandler)
				.failureUrl("/loginForm?error=true").permitAll();
		//@formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHander() {
		return (request, response, authentication) -> {
			val roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			if (roles.contains(RoleName.ADMIN.toSpringRoleName())) {
				response.sendRedirect("/admin/");
			} else {
				response.sendRedirect("/");
			}
		};
	}

}
