package ua.skillsup.javacourse.paintinggallery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.inject.Inject;

/**
 * Created by Shine on 17.04.2016.
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


  @Inject
  UserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/edit/**").hasRole("ADMIN")
            .antMatchers("/*").permitAll()
            .and()
         .formLogin()
            .loginProcessingUrl("/login")
            .loginPage("/login")
            .permitAll()
            .and()
         .logout()
            .permitAll()
            .and()
          .csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new StandardPasswordEncoder();
  }
}
