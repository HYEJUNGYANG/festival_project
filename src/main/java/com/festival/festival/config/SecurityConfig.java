package com.festival.festival.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@Slf4j
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) ->
                                authorizeRequests
//                                .requestMatchers(new AntPathRequestMatcher("/qna/**")).authenticated() //로그인한 유저만 접속가능한 링크
                                        //.requestMatchers(new AntPathRequestMatcher("/admin_page/**")).hasRole("ADMIN") //관리자만 접속가능한 링크*/
//                                .requestMatchers(new AntPathRequestMatcher("/admin_page/admin_login")).permitAll()
                                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("id")
                        .passwordParameter("pw")
                        .loginProcessingUrl("/loginProcess")
                        //.defaultSuccessUrl("/admin_page/admin_main")
                        .successHandler(new CustomLoginSuccessHandler())
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            String uri = request.getHeader("Referer");
                            response.sendRedirect(uri != null ? uri : "");
                        })
                )
                .getOrBuild();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
}
