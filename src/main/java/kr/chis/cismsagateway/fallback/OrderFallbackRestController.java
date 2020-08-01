package kr.chis.cismsagateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author InSeok
 * Date : 2020/08/01
 * Remark :
 */
@RestController
public class OrderFallbackRestController {
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback-CIS");
    }
}
