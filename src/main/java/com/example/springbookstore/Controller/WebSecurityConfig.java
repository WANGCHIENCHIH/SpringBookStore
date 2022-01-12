package com.example.springbookstore.Controller;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /*
    在此設定帳號相關安全設定

     */
    private final PasswordEncoder pwdEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        這裡寫針http的授權設定
         */
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/BookList").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        /*
        此方法會新增user
         */
        UserDetails testuser =
                User.builder()
                        .username("user")
                        .password(pwdEncoder.encode("password"))//密碼加密
                        .roles("USER")
                        .build();
        /*
        新增admin
         */
        UserDetails testadmin =
                User.builder()
                        .username("admin")
                        .password(pwdEncoder.encode("password"))//密碼加密
                        .roles("USER", "ADMIN")
                        .build();


        //開發中暫時顯示密碼
        System.out.printf("%s 's password : %s\n", testuser.getUsername(), testuser.getPassword());
        System.out.printf("%s 's password : %s\n", testadmin.getUsername(), testadmin.getPassword());

        return new InMemoryUserDetailsManager(testuser, testadmin);
    }
}
