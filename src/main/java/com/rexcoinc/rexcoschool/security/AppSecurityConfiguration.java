package com.rexcoinc.rexcoschool.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class AppSecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        String[] publicEndPoints = {"/home", "/holidays/**", "/contact", "/saveMsg", "/courses", "/about", "/login", "/logout", "/assets/**"};
        String[] adminAccessibleEndPoints = {"/displayMessages", "/closeMsg/**"};
        String[] authenticatedEndpoints = {"/dashboard"};

        http.csrf().ignoringRequestMatchers("/saveMsg").and()
                .authorizeHttpRequests()
                .requestMatchers(authenticatedEndpoints).authenticated()
                .requestMatchers(adminAccessibleEndPoints).hasRole("ADMIN")
                .requestMatchers(publicEndPoints).permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();
        return http.build();
    }



//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                .cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(null)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll()
//                .requestMatchers("/login").permitAll()
//                .requestMatchers("/home").permitAll()
//                .anyRequest().authenticated();
//
//        return http.build();
//
////        http.csrf().disable()
////                .authorizeHttpRequests()
////                .requestMatchers(HttpMethod.GET).hasRole("ADMIN")
////                .anyRequest()
////                .authenticated()
////                .and()
////                .httpBasic();
////
////        return http.build();
//
//    }
//


    @Bean
    public InMemoryUserDetailsManager userDetailsService(){

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
//
//    @Bean
//    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
}
