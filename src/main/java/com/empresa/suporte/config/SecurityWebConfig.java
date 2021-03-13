package com.empresa.suporte.config;

import com.empresa.suporte.model.Usuario;
import com.empresa.suporte.security.SecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityDetailsService securityDetailsService;

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                //HABILITAR OU DESABILITAR PAGINAS
                .authorizeRequests()
                .antMatchers("/").permitAll()
                
                //.antMatchers("/usuario/list").hasRole("adm")
                .antMatchers("/usuario/add").permitAll()
                //.antMatchers("/horario/add").hasRole("adm")

                //Quem somos
                .antMatchers("/quemsomos").permitAll()

                //ARTUR MAIN UDYR GOD
                .antMatchers("/usuario/save").permitAll()
                //.antMatchers("/horario/save").hasRole("adm")

                //.antMatchers("/horario/list").hasRole("adm")
                //.antMatchers("/horario/add").hasRole("adm")

                //Habilitar statics
                .antMatchers("/bootstrap-5.0.0/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fullpage/dist/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/font/**").permitAll()
                .antMatchers("/fontAwesome/**").permitAll()
                .antMatchers("/js/**").permitAll()
                //outras autenticações

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                //relembrar usuario logado
                .rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder)throws Exception{
        builder
                .userDetailsService(securityDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    public static void main(String[]args){
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        //SecurityWebConfig.geraSenha("123456");
    }

    public static void geraSenha(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
    }
}
