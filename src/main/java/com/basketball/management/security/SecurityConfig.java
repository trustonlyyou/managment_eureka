package com.basketball.management.security;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableEurekaServer
public class SecurityConfig {

    /**
     * BCrypt 알고리즘으로 패스워드 암호화
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security Filter Chain
     * @param http HttpSecurity
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // CSRF 보호 비활성화 (API 서버나 Eureka 에서는 일반적)
        // 모든 요청은 인증 필요
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated());
        // 브라우저 팝업으로 로그인 (Basic Auth) -> 기본 인증 사용
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
//                .password(bCryptPasswordEncoder().encode("$2a$10$rdC5GpDN5AXJSFS3HpeRwO1HDn5xyM9OLUkuf/t3cwSoC6kW1M5qK"))
                .password("$2a$10$rdC5GpDN5AXJSFS3HpeRwO1HDn5xyM9OLUkuf/t3cwSoC6kW1M5qK")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
