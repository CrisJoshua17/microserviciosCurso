package org.joshua.springcloud.mscv.oauth;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WerbClientConfig {
    


    @LoadBalanced
    @Bean
    WebClient.Builder webClient() {
        return WebClient.builder();
    }
}
