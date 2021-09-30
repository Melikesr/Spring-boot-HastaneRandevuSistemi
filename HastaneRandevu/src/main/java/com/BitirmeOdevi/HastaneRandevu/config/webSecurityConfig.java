package com.BitirmeOdevi.HastaneRandevu.config;

import com.BitirmeOdevi.HastaneRandevu.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/list_users").hasAnyAuthority("Admin")
              .antMatchers("/admin").hasAnyAuthority("Admin")
              .antMatchers("/doctorpanel").hasAnyAuthority("Customer","Admin")

              .antMatchers("/randevu_create").hasAnyAuthority("User","Customer","Admin")
              .antMatchers("/users/edit/").hasAnyAuthority("Admin")
              .antMatchers("/users/save").hasAnyAuthority("Admin","Customer")
              .antMatchers("/departments").hasAnyAuthority("Admin","Customer")
              .antMatchers("/departments/new").hasAnyAuthority("Admin","Customer")
              .antMatchers("/departments/save").hasAnyAuthority("Admin","Customer","User")
              .antMatchers("/departments/edit/*").hasAnyAuthority("Admin","Customer")
              .antMatchers("/departments/delete/*").hasAnyAuthority("Admin","Customer")

              .antMatchers("/doctors").hasAnyAuthority("Admin","Customer")
              .antMatchers("/doctors/new").hasAnyAuthority("Admin","Customer")
              .antMatchers("/doctors/save").hasAnyAuthority("Admin","Customer")
              .antMatchers("/doctors/edit/*").hasAnyAuthority("Admin","Customer")
              .antMatchers("/doctors/delete/*").hasAnyAuthority("Admin","Customer")
              .antMatchers("randevu_create/save").hasAnyAuthority("Admin","Customer")
              .antMatchers("list_randevu").hasAnyAuthority("Admin")
              .antMatchers("/randevu_create/delete/*").hasAnyAuthority("Admin")



              .anyRequest().permitAll()
              .and()
              .formLogin()
                .usernameParameter("email")
                 .defaultSuccessUrl("/Anasayfa")
                 .permitAll()
              .and()
              .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

      http.csrf().disable();
      http.headers().frameOptions().disable();
    }

    @Autowired
    private LoginSuccessHandler successHandler;
}
