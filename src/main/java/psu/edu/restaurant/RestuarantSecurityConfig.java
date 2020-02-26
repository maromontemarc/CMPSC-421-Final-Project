package psu.edu.restaurant;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.naming.AuthenticationException;

@Configuration
public class RestuarantSecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected  void configure(AuthenticationManagerBuilder auth)  throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/cust/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cust/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/cust/get").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/cust/list").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/menu/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/menu/update").hasRole("ADMIN")

                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
