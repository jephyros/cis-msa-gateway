package kr.chis.cismsagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CisMsaGatewayApplication {

    public static void main(String[] args) {
        //
        SpringApplication.run(CisMsaGatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(p -> p
//                        .path("/api/order/list")
//                        .filters(f ->
//                            f.hystrix(config -> config
//                                    .setName("CISCMD")
//                                    .setFallbackUri("forward:/fallback"))
//
//                        )
//                        .uri("http://localhost:8180"))
//                .build();

//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri("http://httpbin.org:80"))
//                .route(p -> p
//                        .host("*.hystrix.com")
//                        .filters(f -> f.hystrix(config -> config
//                                .setName("mycmd")
//                                .setFallbackUri("forward:/fallback")))
//                        .uri("http://httpbin.org:80"))
//                .build();
//
//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri("http://httpbin.org:80"))
//                .route(p -> p
//                        .host("*.hystrix.com")
//                        .filters(f -> f.hystrix(config -> config.setName("mycmd")))
//                        .uri("http://httpbin.org:80")).
//                        build();
//

//                .route(p -> p
//                        .path("/api/order/list")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        //.uri("http://httpbin.org:80"))
//                        .uri("http://localhost:8180"))
//                //.uri("http://localhost:8180/api/order/list2?page=1&size=4"))
//                .build();

//    }
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().build();
//    }

}
