package com.musicplatform;

import com.musicplatform.entities.Band;
import com.musicplatform.entities.Listener;
import com.musicplatform.entities.Musician;
import com.musicplatform.services.userDetailsServices.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled =  true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .authorizeRequests().antMatchers("/").hasAnyAuthority(Listener.authority, Band.authority, Musician.authority)
                .and()
                .authorizeRequests().antMatchers("/listener/**").hasAnyAuthority(Listener.authority)
                .and()
                .authorizeRequests().antMatchers("/musician/**").hasAnyAuthority(Musician.authority)
                .and()
                .authorizeRequests().antMatchers("/band/**").hasAnyAuthority(Band.authority)
                .and()
                .authorizeRequests().antMatchers("/error").permitAll()
                .and()
                .authorizeRequests().antMatchers("/test").permitAll()
                .and()
                .sessionManagement().maximumSessions(-1).sessionRegistry(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
