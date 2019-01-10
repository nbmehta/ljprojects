package com.ljproject.configuration;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.ljproject.handler.CustomSuccessHandler;
import com.ljproject.security.JwtAuthenticationFilter;
import com.ljproject.serviceImpl.CustomUserDetailsService;






@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	

	 @Autowired
	 CustomUserDetailsService customUserDetailsService;

	 @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        return new JwtAuthenticationFilter();
	 }

	
   
	@Autowired
	CustomSuccessHandler customSuccesshandler;
	

	 @Bean(BeanIds.AUTHENTICATION_MANAGER)
	 @Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
		 userDetailsService(customUserDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// http
        // .cors()
         //    .and()
         //.csrf()
          //   .disable()
         //.exceptionHandling()
         //    .authenticationEntryPoint(unauthorizedHandler)
          //   .and()
         //.sessionManagement()
         //    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         //    .and()
        // .authorizeRequests()
         //    .antMatchers("/",
         //        "/favicon.ico",
         //        "/**/*.png",
         //        "/**/*.gif",
         //        "/**/*.svg",
         //        "/**/*.jpg",
         //        "/**/*.html",
         //        "/**/*.css",
         //        "/**/*.js")
         //        .permitAll()
         //    .antMatchers("/api/auth/**")
         //        .permitAll()
         //    .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
         //        .permitAll()
         //    .antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
         //        .permitAll()
          //   .anyRequest()
          //      .authenticated();

		
		
		http.csrf().disable()
				.authorizeRequests()
			    .antMatchers("/webjars/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/test").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/forgotPassword").permitAll()
				.antMatchers("/reset/**").permitAll()
				.antMatchers("/approve/**").permitAll()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/user/**").access("hasRole('ROLE_USER')").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.successHandler(customSuccesshandler)
//				.failureHandler(customAuthFailureHandler)
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.deleteCookies("remember-me").permitAll().and()
				.rememberMe().tokenValiditySeconds(180).and()
				.logout().logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
		
		 // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}