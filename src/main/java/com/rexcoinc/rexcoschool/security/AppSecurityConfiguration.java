package com.rexcoinc.rexcoschool.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
       return http.csrf().disable()
               .authorizeHttpRequests()
               .requestMatchers("/dashboard").authenticated()
               .requestMatchers("/home").permitAll()
               .requestMatchers("/holidays/**").permitAll()
               .requestMatchers("/assets/**").permitAll()
               .requestMatchers("/contact").permitAll()
               .requestMatchers("/saveMsg").permitAll()
               .requestMatchers("/courses").permitAll()
               .requestMatchers("/about").permitAll()
               .requestMatchers("/login").permitAll()
               .and()
               .formLogin()
               .loginPage("/login")
               .defaultSuccessUrl("/dashboard")
               .failureUrl("/login?error=true").permitAll()
               .and()
               .logout()
               .logoutSuccessUrl("/login?logout=true")
               .invalidateHttpSession(true).permitAll()
               .and().httpBasic()
               .and().build();
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
//
//
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Emeka")
                .password("pass")
                .roles("ADMIN")
                .build();


        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("Joe")
                .password("p@ss")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user, user1);
    }

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
