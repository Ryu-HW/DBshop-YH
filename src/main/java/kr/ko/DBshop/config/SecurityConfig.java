package kr.ko.DBshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().permitAll()
                )

                .formLogin(auth->auth
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureHandler(new CustomAuthenticationFailureHandler())
                        .permitAll()
                )

                .logout(auth->auth
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                );

        http
                .sessionManagement(auth -> auth
                        .maximumSessions(1) //최대 몇 개의 세션을 만들 수 있는지.
                        .maxSessionsPreventsLogin(true) //true는 새로운 로그인 차단, false는 기존 로그인 세션 삭제
                );


//        http
//                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

//    필수로있어야함!!!
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }


}
