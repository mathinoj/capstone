package com.codeup.halfguard.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    private final UserDetailsLoader usersLoader;

    public Security(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authorization = new DaoAuthenticationProvider();
        authorization.setUserDetailsService(userDetailsService());
        authorization.setPasswordEncoder(passwordEncoder());

        return authorization;
    }
//COMMENTED OUT ON FEB 15, @129PM**************


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .usernameParameter("username")
//                .loginPage("/login")
                .defaultSuccessUrl("/posts") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/", "/posts") // anyone can see the home and the ads pages
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .antMatchers(
                        "list_users",
                        "/posts/create", // only authenticated users can create posts
                        "/posts/edit/{id}", // only authenticated users can edit posts
                        "/posts/delete/{id}"
                )
                .authenticated()
                .anyRequest().permitAll()

        ;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                /* Login configuration */
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/posts") // user's home page, it can be any URL
//                .permitAll() // Anyone can go to the login page
//                /* Logout configuration */
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout") // append a query string value
//                /* Pages that can be viewed without having to log in */
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/posts") // anyone can see the home and the ads pages
//                .permitAll()
//                /* Pages that require authentication */
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        "/posts/create", // only authenticated users can create ads
//                        "/posts/{id}/edit" // only authenticated users can edit ads
//                )
//                .authenticated()
//        ;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }
}






