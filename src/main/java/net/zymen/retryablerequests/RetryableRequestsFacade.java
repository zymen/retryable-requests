package net.zymen.retryablerequests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class RetryableRequestsFacade {

    @Bean
    public OncePerRequestFilter createCacheResponseRequestFilter(
            RequestMappingHandlerMapping requestMappingHandlerMapping) {

        return new CacheResponseRequestFilter(
            new RequestToChecksumService(),
                requestMappingHandlerMapping);
    }
}
