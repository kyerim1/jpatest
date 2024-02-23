package com.example.securitytest.auth;

import com.example.securitytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.net.URLEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    UserService userService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .mvcMatchers("/image/**","/css/**", "/javascript/**").permitAll()
                .mvcMatchers("/", "/guest/**", "/join").permitAll()
                .mvcMatchers("/member/**").hasAnyRole("USER","ADMIN")
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;
        http
                .formLogin()
                .loginPage("/login") // 커스텀 로그인 주소
                .loginProcessingUrl("/login_chk") // 로그인 처리 주소
                .defaultSuccessUrl("/",true) //로그인 성공시 이동할 페이지
                .usernameParameter("id") //커스텀 html에 input 태그 name값
                .passwordParameter("pw") // 커스텀 html에 input 태그 name
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")  //로그아웃 성공시 이동할 페이지
                .invalidateHttpSession(true) // 세션제거
                .permitAll();

        http.csrf().disable(); // 토큰 인증 , 클라리언트와 서버의 상호 작용 으로 사용, 정상적인 방법으로

        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





//    @Autowired
//    private  AuthenticationFailureHandler authenticationFailureHandler;


//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//    //    http.formLogin().disable(); //  시큐리티 기본 로그인폼 비활성화
//
////        http.authorizeRequests()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                    .loginPage("/login").permitAll().and()
////                .logout().permitAll();    커스텀 로그인 페이지 사용
//
//        http.authorizeRequests()
//                .mvcMatchers("/image/**","/css/**", "/javascript/**").permitAll()
//                .mvcMatchers("/", "/guest/**", "/join").permitAll()
//                .mvcMatchers("/member/**").hasAnyRole("USER","ADMIN")
//                .mvcMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//        ;
//        http
//                .formLogin()
//                    .loginPage("/login") // 커스텀 로그인 주소
//                    .loginProcessingUrl("/login_chk") // 로그인 처리 주소
//                    //.failureUrl("/login?error") // 로그인 실패시 이동 주소
//                    .failureHandler(loginfail())
//                    .defaultSuccessUrl("/",true) //로그인 성공시 이동할 페이지
//                    .usernameParameter("id") //커스텀 html에 input 태그 name값
//                    .passwordParameter("pw") // 커스텀 html에 input 태그 name값
//                    .permitAll()
//                    .and()
//                .logout().logoutUrl("/logout")
//                    .logoutSuccessUrl("/")  //로그아웃 성공시 이동할 페이지
//                    .invalidateHttpSession(true) // 세션제거
//                    .permitAll();
//
//        http.logout().permitAll();
//
//        http.csrf().disable(); // 토큰 인증 , 클라리언트와 서버의 상호 작용 으로 사용, 정상적인 방법으로
//                               // 접근 하고 있는가 확인용
//
//        //  1' or 1='1
//// select * from member where id='aaa' and pw='1' or 1='1';
//
//        return http.build();
//    }
//


//    @Bean
//    public UserDetailsService userDetailsService() {
//        //UserDetailsManager를 사용하여 간단한 사용자 정보를 메모리에 저장
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user").password("{noop}1234").roles("USER").build(),
//                User.withUsername("admin").password("{noop}1234").roles("ADMIN").build()
//        );
//        //DB 조회 작업도 여기서 할수 있다.
//    }
//
//    @Bean
//    public CustomAuthFailureHandler loginfail(){
//        return new CustomAuthFailureHandler();
//    }
//




}
