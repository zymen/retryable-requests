package net.zymen.retryablerequests;

import net.zymen.retryablerequests.annotations.RetryableRequest
import net.zymen.retryablerequests.storage.ResponseStorage
import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

internal class CacheResponseRequestFilter(
        private val requestToChecksumService: RequestToChecksumService,
        private val responseStorage: ResponseStorage,
        private val requestMappingHandlerMapping: RequestMappingHandlerMapping
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {

        try {
            val handlerExecutionChain = requestMappingHandlerMapping.getHandler(request)

            if (handlerExecutionChain != null) {
                val handlerMethod = handlerExecutionChain.handler

                if (handlerMethod is HandlerMethod &&
                        handlerMethod.hasMethodAnnotation(RetryableRequest::class.java)) {
                    log.info("Has annotation {}", handlerMethod.hasMethodAnnotation(RetryableRequest::class.java))

                    val key = requestToChecksumService.requestToChecksum(request);
                    log.info("key - {}", key);

                    if (responseStorage.hasKey(key)) {
                        replayRecordedResponse(key, response)
                    } else {
                        recordResponse(response, filterChain, request, key)
                    }
                } else {
                    filterChain.doFilter(request, response)
                }
            } else {
                filterChain.doFilter(request, response)
            }
        } catch (e: Exception) {
            log.error("errr", e);
        }
    }

    private fun recordResponse(response: HttpServletResponse, filterChain: FilterChain, request: HttpServletRequest, key: String) {
        val servletResponse = ResponseRecorderWrapper(response)
        filterChain.doFilter(request, servletResponse)

        responseStorage.set(key, servletResponse.recordedContent)
    }

    private fun replayRecordedResponse(key: String, response: HttpServletResponse) {
        val content = responseStorage.get(key)
        log.info("Found content for key ${key} - ${content}")

        response.outputStream.write(content.toByteArray())
        response.addHeader("Content-Type", "application/json")
    }

    companion object {
        private val log = LoggerFactory.getLogger(CacheResponseRequestFilter::class.java)
    }
}
