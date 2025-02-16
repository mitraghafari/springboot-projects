package com.example.viewWithSecurity;

import com.example.viewWithSecurity.exception.AuthFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("userDetailsServiceImp")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()//check authentication
                .antMatchers("/actuator/**", "/register", "/error", "/checkexc").permitAll()//do not redirect to login, auth not needed
                .anyRequest().authenticated()//all other need authentication
                .and()
//                .httpBasic()//get user pass from httpbasic
                .formLogin()//get user pass from form
                .loginPage("/login")//login url , it needs a controller
                .permitAll()
//                .usernameParameter("username")
//                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
//                .failureUrl("/error")  or failure handler as bellow
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout") // it is default url for logout, we can change url. , it needs a controller
               // .logoutSuccessUrl("/usermanagemnt/login") //or logout handler as bellow
//                .logoutSuccessHandler()
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                //always use following codes to get detailed error instead of 403:forbidden
                //if we set /error, no detailed error page(whiteable..) has been seen
                .cors()
                .and()
                .csrf().disable();
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthFailureHandler();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    //the following codes are from my travix code:
    //i get user pass from httpbasic in request and math them with in-memory use-pass
//    @Value("${app.api.password}")
//    private String password;
//
//    @Value("${app.api.username}")
//    private String username;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(username)
//                .password("{noop}"+password)
//                .roles("USER");
//    }
}
