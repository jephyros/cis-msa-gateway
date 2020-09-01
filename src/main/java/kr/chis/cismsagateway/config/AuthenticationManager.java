package kr.chis.cismsagateway.config;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author InSeok
 * Date : 2020/08/15
 * Remark :
 */
@Component
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        List<GrantedAuthority> authorities = new ArrayList<>();

        //임시 강제로그인
        if (1==1) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return Mono.just(new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), null, authorities));
        }
        //임시 강제로그인

        //jwt 토근 검증 ?
        try {
            DecodedJWT decodedJWT = jwtUtil.verifyToken(authToken);
//
            Map<String, Claim> claims = decodedJWT.getClaims();    //Key is the Claim name



            for(String role : claims.get("authorities").asArray(String.class)){
                log.info("{} JWT Role : {}",claims.get("user_name").asString(),role);
                authorities.add(new SimpleGrantedAuthority(role));
            }

            return Mono.just(new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),null,authorities));

        } catch (Exception e) {
            e.printStackTrace();
            return Mono.empty();
        }
    }
}
