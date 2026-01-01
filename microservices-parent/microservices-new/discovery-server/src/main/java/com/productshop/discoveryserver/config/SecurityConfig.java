//package com.productshop.discoveryserver.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class SecurityConfig {
//    @Value("${eureka.username}")
//    private String username;                //from application.properties file
//
//    @Value("${eureka.password}")
//    private String password;
//
////    @Override
////    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
////        authenticationManagerBuilder.inMemoryAuthentication()
////                .passwordEncoder(NoOpPasswordEncoder.getInstance())
////                .withUser(username).password(password)
////                .authorities("USER");
////    }
//
////    @Bean
//////    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder){
////    public UserDetailsService userDetailsService(NoOpPasswordEncoder noOpPasswordEncoder){
////        InMemoryUserDetailsManager manager= new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("eureka")
////                .password(noOpPasswordEncoder.encode("password"))
////                .roles("USER")
////                .build());
////
////        manager.createUser(User.withUsername("admin")
////                .password(noOpPasswordEncoder.encode("adminPass"))
////                .roles("USER", "ADMIN")
////                .build());
////        return manager;
////    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username(username)
//                .password(password)
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
////    @Override
////    public void configure(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.csrf()
////                .disable()
////                .authorizeRequests()
////                .anyRequest()
////                .authenticated()
////                .and()
////                .httpBasic();
////    }
//
////    @Bean
////    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder, UserDetailsService userDetailsService) throws Exception{
////        return http.getSharedObject(AuthenticationManagerBuilder.class)
////                .userDetailsService(userDetailsService)
////                .passwordEncoder(noOpPasswordEncoder)
////                .and()
////                .build();
////
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//
//        return http.build();
//    }
//
//
//}