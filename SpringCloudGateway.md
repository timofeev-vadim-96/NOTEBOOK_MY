

Predicate - условие, при котором будет срабатывать маршрутизация

> Правила маршрутизации лучше описывать в application.yml для того, чтобы не нужно было пересобирать проект при изменении конфига (адресов микросервисов)

[Пример конфига OTUS](https://github.com/petrelevich/jvm-digging/blob/main/spring-cloud/api-gateway/src/main/java/ru/demo/config/ApplConfigProperties.java) 

`Гейтвей в java-стиле`:  
```java
@Configuration
@EnableConfigurationProperties(ApplConfigProperties.class)
@EnableDiscoveryClient
public class ApiConfig {

    @Bean
    public XrequestFilter xrequestFilter() {
        return new XrequestFilter();
    }

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb, ApplConfigProperties applConfigProperties, XrequestFilter xrequestFilter) {
        var routesBuilder = rlb.routes();
        for (var route : applConfigProperties.getApiRoutes()) {
            routesBuilder.route(route.id(), routeSpec ->
                    routeSpec
                            .path(String.format("/%s/**", route.from()))
                            .filters(fs -> fs.filters(xrequestFilter)
                                            .rewritePath(String.format("/%s/(?<segment>.*)", route.from()), "/${segment}")
                            )
                            .uri(String.format("%s/@", route.to()))
            );
        }
        return routesBuilder.build();
    }
}
```

Пример `GatewayFilter`
```java
import org.springframework.web.server.ServerWebExchange;
import java.util.UUID;

public class XRequestFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String guid = UUID.randomUUID().toString();

        var request = exchange.getRequest().mutate()
                .header("X-Request-Id", guid)
                .build();

        return chain.filter(exchange.mutate().request(request).build());
    }
}
```


