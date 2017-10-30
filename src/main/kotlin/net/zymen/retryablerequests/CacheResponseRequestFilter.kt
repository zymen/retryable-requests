package net.zymen.retryablerequests;

import net.zymen.retryablerequests.annotations.RetryableRequest
import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

internal class CacheResponseRequestFilter(
        private val requestToChecksumService: RequestToChecksumService,
        private val requestMappingHandlerMapping: RequestMappingHandlerMapping
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {

        try {
            val handlerMethod = requestMappingHandlerMapping.getHandler(request).getHandler()

            if (handlerMethod is HandlerMethod) {
                log.info("Has annotation {}", handlerMethod.hasMethodAnnotation(RetryableRequest::class.java))
                log.info("test {}", requestMappingHandlerMapping.getHandler(request))
            }
        } catch (e: Exception) {
            log.error("errr", e);
        }

        val key = requestToChecksumService.requestToChecksum(request);
        log.info("key - {}", key);

        filterChain.doFilter(request, ResponseRecorderWrapper(response));
    }

    companion object {
        private val log = LoggerFactory.getLogger(CacheResponseRequestFilter::class.java)
    }
}
