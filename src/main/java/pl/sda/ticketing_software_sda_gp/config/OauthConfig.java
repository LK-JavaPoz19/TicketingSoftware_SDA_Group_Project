package pl.sda.ticketing_software_sda_gp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//@EnableWebSecurity
//@Configuration
public class OauthConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
////        http.antMatcher("/**")
////                .authorizeRequests()
////                .antMatchers("/","/login/**")
////                .permitAll()
////                .anyRequest()
////                .authenticated()
////        ;
//
//        http.antMatcher("/**").authorizeRequests()
//                .antMatchers("/", "/login**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login();
//
//    }
//
//    /////////////////////////////////////////////////
//
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .usersByUsernameQuery("select username, password, enabled "
//                        + "from `user` "
//                        + "where username=?")
//                .authoritiesByUsernameQuery("select username , user_type_name "
//                        + "from `user` join user_type on `user`.user_type_user_type_id = user_type.user_type_id "
//                        + "where username=?");
//
//
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//        @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}