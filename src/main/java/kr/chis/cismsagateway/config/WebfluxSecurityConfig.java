package kr.chis.cismsagateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
        http.cors();
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8190"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
