package kr.chis.cismsagateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author InSeok
 * Date : 2020/08/15
 * Remark : Webflux Security 설정
 */
@EnableWebFluxSecurity
public class WebfluxSecurityConfig {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){

        http.httpBasic().disable();
        http.formLogin().disable();
        http.csrf().disable();
        http.logout().disable();

        http.authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository);


        http.authorizeExchange()
                .pathMatchers("/oauth/**").permitAll()
                .pathMatchers("/**").hasAnyRole("ADMIN")
                //.authenticated()
        ;


        return http.build();
    }
}
