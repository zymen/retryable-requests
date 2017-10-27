package net.zymen.retryablerequests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class RetryableRequestsFacade {

    @Bean
    public OncePerRequestFilter createCacheResponseRequestFilter() {
        return new CacheResponseRequestFilter(
            new RequestToChecksumService()
        );
    }
}
