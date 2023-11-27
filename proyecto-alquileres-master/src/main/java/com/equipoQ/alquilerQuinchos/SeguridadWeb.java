package com.equipoQ.alquilerQuinchos;
import com.equipoQ.alquilerQuinchos.Excepciones.*;
import com.equipoQ.alquilerQuinchos.Servicios.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter{
   MiExcepcion ex ;
@Autowired
public UsuarioServicio usuarioServicio;
@Autowired
//recibe un objeto del tipo authetication
public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
//   AuthenticationManagerBuilder apodado auth es un manejador de seguridad de springSecurity.
//   el servicio utilizado para autenticar un usuario es userDetailsService que se encuentra en (usuarioServicio)
   auth.userDetailsService(usuarioServicio)
//         una vez que se encuentra este usuario para codificar la contraseña se utiliza la siguiente linea
           .passwordEncoder(new BCryptPasswordEncoder());
}
   
@Override
    public void configure(HttpSecurity http) throws Exception{
    
        http
                .authorizeRequests()
                    .antMatchers("/admin/*").hasRole("ADMIN")
                    .antMatchers("/css/*", "/js/*", "/img/*", "/**")
                    .permitAll()
                .and().formLogin()
                      .loginPage("/login")
                      .loginProcessingUrl("/logincheck")
                      .usernameParameter("email")
                      .passwordParameter("password")
                      .defaultSuccessUrl("/inicio")
                      .permitAll()
                .and().logout()
                      .logoutUrl("/logout")
                      .logoutSuccessUrl("/login")
                      .permitAll()
                .and().csrf().disable();
    }
}