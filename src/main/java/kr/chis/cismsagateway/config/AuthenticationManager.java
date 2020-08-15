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

        //jwt 토근 검증 ?
        try {
            DecodedJWT decodedJWT = jwtUtil.verifyToken(authToken);
//
            Map<String, Claim> claims = decodedJWT.getClaims();    //Key is the Claim name
            Iterator<String> iterator = claims.keySet().iterator();
            iterator.forEachRemaining(v->{
                Claim claim = claims.get(v);
                log.info("{}: {}",v, claim.asString());
            });
            for(String x : claims.get("authorities").asArray(String.class)){
                System.out.println("========== : " + x);
            }


            System.out.println("==============================================");
            System.out.println(decodedJWT.getExpiresAt());
            System.out.println(decodedJWT.getClaim("exp").asString());
            System.out.println("==============================================");

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return Mono.just(new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),null,authorities));

        } catch (Exception e) {
            e.printStackTrace();
            return Mono.empty();
        }
    }
}
