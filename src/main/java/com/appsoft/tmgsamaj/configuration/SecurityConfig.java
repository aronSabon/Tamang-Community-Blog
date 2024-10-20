package com.appsoft.tmgsamaj.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.appsoft.tmgsamaj.serviceImpl.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
	private MyUserDetailsServiceImpl userDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//disabel csrf
		http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/login", "/register","/css/**", "/js/**", "/images/**","/webjars/**","/","/assets/**","/f**", "/committeeMemberList1","/eventImages/**").permitAll() // allow access to these pages without authentication
                .anyRequest().authenticated() // all other requests require authentication
            );
        http.formLogin(form -> form
                .loginPage("/login")             // URL of the login page
                .loginProcessingUrl("/login")    // URL where login form is submitted
                .successHandler(new CustomAuthenticationSuccessHandler()) // Custom success handler
                .permitAll()                     // Allow everyone to access the login page
            );
        http.logout(logout -> logout
        		.logoutUrl("/logout") // URL to submit the logout POST request
        	    .logoutSuccessHandler((request, response, authentication) -> {
        	        System.out.println("Logout successful for user: " + (authentication != null ? authentication.getName() : "anonymous"));
        	        response.sendRedirect("/login?logout"); // Redirect after successful logout
        	    })
        	    .invalidateHttpSession(true) // Invalidate the session
        	    .deleteCookies("JSESSIONID") // Clear the JSESSIONID cookie
        	    .permitAll()
            );
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.maximumSessions(1)
		  	    .expiredUrl("/login?expired=true"));
		return http.build();	
	}
//	@Bean 
//	public UserDetailsService userDetailService() {
//		UserDetails user1 = User.withDefaultPasswordEncoder().username("admin").password("admin").build();
//		return new InMemoryUserDetailsManager(user1);
//	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}
