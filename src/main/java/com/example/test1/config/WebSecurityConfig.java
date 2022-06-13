//package com.example.test1.config;
//
//import com.example.test1.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource base;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//
//                .authorizeRequests()
//                .antMatchers("/app/root", "/registration", "/a", "/","/send").permitAll()
//                .anyRequest().authenticated()
//                .and();
//
//
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
//
//    }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication()
//                    .dataSource(base)
//                    .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                    .usersByUsernameQuery("select username, password, active from usr where username=?")
//                    .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
//        }
//}
