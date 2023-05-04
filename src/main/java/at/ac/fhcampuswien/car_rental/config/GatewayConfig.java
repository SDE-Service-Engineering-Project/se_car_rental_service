package at.ac.fhcampuswien.car_rental.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("booking-service", r -> r.path("/api/v1/bookings/**", "/api/v1/currency/**")
                        .uri("http://se-booking-service-part2/"))
                .route("car-service", r -> r.path("/api/v1/cars/**")
                        .uri("http://se-car-service-part2/"))
                .build();
    }
}
