package com.example.shoppingMall2.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean // 패스워드 암호화 메소드 빈으로 등록
	public BCryptPasswordEncoder BCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 인증하지 않겠다(permitAll)나머지는 인증을 거친다(anyRequest)
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/members/**").hasAnyRole("ADMIN", "MEMBER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
				);
		
		// 로그인에 대한 설정
		http.formLogin((auth) -> auth
				.loginPage("/members/loginForm") // 로그인 폼 지정(직접 만든 로그인 페이지 경로) - 쓰지 않으면 Spring Security가 제공하는 로그인 폼 사용
				.loginProcessingUrl("/members/loginProc")// 로그인 폼 지정 후 폼 파라미터 보내는 경로 지정(실제로 처리하는 url, 컨트롤러를 만들지 않음) - 컨트롤러에 직접 만들지 않는다.(Spring Security)
				.defaultSuccessUrl("/")
				//.failureUrl("/login?error=true")
			    .permitAll() //.defaultSuccessUrl("/") // 로그인 성공했을 때 갈 페이지
				);
		
		http.formLogin((auth) -> auth
				.successHandler((request, response, authentication) ->{
					authentication.getAuthorities().forEach(authority ->{
					if (authority.getAuthority().equals("ROLE_ADMIN")) {
						 try {
							response.sendRedirect("/admin/main");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						try {
							response.sendRedirect("/");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					});
				})
				);
        
		http.logout((auth) -> auth
				.logoutSuccessUrl("/")
				
				);
		
		http.exceptionHandling((auth) -> auth
				.accessDeniedPage("/custom403")
				);
		
		http.csrf(AbstractHttpConfigurer::disable); //csrf를 form 테이터에서 사용하지 않을 때/csrf 기능 끄기
		
		return http.build();
	}
	
}
