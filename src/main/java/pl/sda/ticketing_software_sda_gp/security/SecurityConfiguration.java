package pl.sda.ticketing_software_sda_gp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .usersByUsernameQuery("select username, password, enabled "
                        + "from `user` "
                        + "where username=?")
                .authoritiesByUsernameQuery("select username , user_type_name "
                        + "from `user` join user_type on `user`.user_type_user_type_id = user_type.user_type_id "
                        + "where username=?");

//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("admin")
//                .roles("CONSULTANT");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/tickets").hasAnyRole("CLIENT", "CONSULTANT", "ADMIN")
//                .antMatchers("/tickets/?").hasAnyRole("CLIENT", "CONSULTANT", "ADMIN")
//                .antMatchers("/tickets/?/messages").hasAnyRole("CLIENT", "CONSULTANT", "ADMIN")
//                .antMatchers("/conversations").hasAnyRole("CONSULTANT", "ADMIN")
//                .antMatchers("/users", "/users/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
