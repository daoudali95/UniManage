package com.example.unimanagement.security;

import com.example.unimanagement.filter.CustomAuthenticationFilter;
import com.example.unimanagement.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor());
    }

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor(tokenWrapper());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TokenWrapper tokenWrapper(){
        return new TokenWrapper();
    }

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());//it helps in changing the url
        customAuthenticationFilter.setFilterProcessesUrl("/uni/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);//STATELESS means no session will be created by the sprig boot
        http.authorizeRequests().antMatchers("/uni/login/**","/uni/token/refresh").permitAll();
        http.authorizeRequests().antMatchers(POST, "/uni/user/save/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/uni/delUser/id/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/uni/users/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/user/{id}").hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers(POST, "/uni/role/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/uni/role/addtouser/**").hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers(POST, "/uni/book/**").hasAnyAuthority("ROLE_LIBRARIAN");
        http.authorizeRequests().antMatchers(GET, "/uni/book/{id}/**").hasAnyAuthority("ROLE_LIBRARIAN");
        http.authorizeRequests().antMatchers(GET, "/uni/AllBooks/**").hasAnyAuthority("ROLE_LIBRARIAN");
        http.authorizeRequests().antMatchers(DELETE, "/uni/delBook/{id}/**").hasAnyAuthority("ROLE_LIBRARIAN");

        http.authorizeRequests().antMatchers(POST, "/uni/course/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/uni/Allcourses/**").hasAnyAuthority("ROLE_ADMIN","ROLE_TEACHER","ROLE_STUDENT");
        http.authorizeRequests().antMatchers(POST, "/uni/addCourses/{username}").hasAnyAuthority("ROLE_ADMIN","ROLE_TEACHER","ROLE_STUDENT");
        http.authorizeRequests().antMatchers(GET, "/uni/course/{id}/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/uni/delCourse/{id}/**").hasAnyAuthority("ROLE_ADMIN","ROLE_TEACHER","ROLE_STUDENT");


        http.authorizeRequests().antMatchers(GET, "/uni/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/uni/order/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET, "/uni/Allproducts/**").hasAnyAuthority("ROLE_USER");

        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}