package net.zymen.retryablerequests;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

class CacheResponseRequestFilter extends OncePerRequestFilter {
    private static final Logger log = getLogger(CacheResponseRequestFilter.class);

    private RequestToChecksumService requestToChecksumService;

    @Autowired
    public CacheResponseRequestFilter(RequestToChecksumService requestToChecksumService) {
        this.requestToChecksumService = requestToChecksumService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String key = requestToChecksumService.requestToChecksum(request);
        log.info("key - {}", key);

        filterChain.doFilter(request, new ResponseRecorderWrapper(response));
    }
}
